package com.jennifer.controller;

import com.jennifer.entity.*;
import com.jennifer.entity.ShippingAddress;
import com.jennifer.service.*;
import com.jennifer.util.AppUtil;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Handles requests from Customers
 */

@Controller
@SessionAttributes({"favoriteBag", "shoppingBag"})
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private FavoriteProductService favoriteProductService;
    private UserInfoService userInfoService;
    private ShoppingProductService shoppingProductService;
    private MarketingCampaignService marketingCampaignService;
    private ProductInfoService productInfoService;
    private ShippingAddressService shippingAddressService;
    private DeliveryMethodService deliveryMethodService;
    private OrderInfoService orderInfoService;
    private PaymentInvoiceService paymentInvoiceService;
    private Map<String, String> map = new HashMap<>();

    @Autowired
    public CustomerController(MarketingCampaignService marketingCampaignService, UserInfoService userInfoService, ProductInfoService productInfoService,
            FavoriteProductService favoriteProductService, ShoppingProductService shoppingProductService, ShippingAddressService shippingAddressService,
                              DeliveryMethodService deliveryMethodService, OrderInfoService orderInfoService, PaymentInvoiceService paymentInvoiceService){
        this.marketingCampaignService = marketingCampaignService;
        this.userInfoService = userInfoService;
        this.productInfoService = productInfoService;
        this.favoriteProductService = favoriteProductService;
        this.shoppingProductService = shoppingProductService;
        this.shippingAddressService = shippingAddressService;
        this.deliveryMethodService = deliveryMethodService;
        this.orderInfoService = orderInfoService;
        this.paymentInvoiceService = paymentInvoiceService;
    }

    @Value("${clientID}")
    private String clientID;

    @Value("${clientSecret}")
    private String clientSecret;

    @ModelAttribute("favoriteBag")
    public List<ProductInfo> createFavoriteBag(){
        log.info("create favorite bag !");
        UserInfo userInfo = AppUtil.getCustomerFromSession();
        if(userInfo != null) {
            List<ProductInfo> productInfoList = new ArrayList<>();
            List<FavoriteProduct> favoriteProductList = favoriteProductService.findAllByUserId(userInfo.getId());
            for(FavoriteProduct favoriteProduct : favoriteProductList){
                productInfoList.add(favoriteProduct.getProductInfo());
            }
            return productInfoList;
        } else {
            return new ArrayList<>();
        }
    }

    @ModelAttribute("shoppingBag")
    public Map<ProductInfo, Integer> createShoppingBag(){
        log.info("create cart");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null && !shoppingProductService.findAllByUserId(userInfo.getId()).isEmpty()) {
            Map<ProductInfo, Integer> map = new HashMap<>();
            for (ShoppingProduct shoppingProduct : userInfo.getShoppingProducts()){
                map.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
            }
            return map;
        } else {
            return new HashMap<>();
        }
    }

    @GetMapping("/")
    public String customer_homepage(Model model){
        model.addAttribute("availableCampaigns", marketingCampaignService.getAvailableCampaigns());
        model.addAttribute("availableProducts", productInfoService.getLatestProducts());

        return "index";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping("/information")
    public String information(Model model, @ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag){
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo == null){
            return "redirect:/login";
        } else if(shoppingBag.size() == 0){
            return "redirect:/";
        } else {
            model.addAttribute("confirmShipping", shippingAddressService.findByUser(userInfo));
            model.addAttribute("deliveryMethods", deliveryMethodService.findAllAvailable());

            return "information";
        }

    }

    @GetMapping("/checkout")
    public String checkoutGet(){
        return "redirect:/";
    }

    @PostMapping("/checkout")
    public String checkout(HttpServletRequest req, @ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag){
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo == null){
            return "redirect:/login";
        } else {
            String payMethod = req.getParameter("payBy");
            req.getSession().setAttribute("confirmPayment", payMethod);

            if("pay-online".equals(payMethod)){
                int shippingId = Integer.parseInt(req.getParameter("shipToAddress"));
                ShippingAddress confirmShipping = shippingAddressService.findById(shippingId);
                req.getSession().setAttribute("confirmShipping", confirmShipping);

                int deliveryId = Integer.parseInt(req.getParameter("deliveryMethods"));
                DeliveryMethod confirmDelivery = deliveryMethodService.findById(deliveryId);
                req.getSession().setAttribute("confirmDelivery", confirmDelivery);

                executePaypalPayment(req, confirmDelivery, shoppingBag);
            } else {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                double total = 0;

                for (Object o : shoppingBag.entrySet()) {
                    Map.Entry pair = (Map.Entry) o;
                    ProductInfo productInfo = (ProductInfo) pair.getKey();
                    int quantity = (int) pair.getValue();
                    total += Double.valueOf(df.format((productInfo.getUnitPrice().doubleValue() * (100 - productInfo.getDiscount())/100) * quantity));
                }
                req.getSession().setAttribute("orderAmount", df.format(total));
            }

            return "checkout";
        }

    }

    private Payment executePaypalPayment(HttpServletRequest req, Object ... objects){
        Payment createdPayment = null;
        APIContext apiContext = new APIContext(clientID, clientSecret, "sandbox");

        if (req.getParameter("PayerID") != null) {
            Payment payment = new Payment();
            if (req.getParameter("guid") != null) {
                payment.setId(map.get(req.getParameter("guid")));
            }

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(req.getParameter("PayerID"));
            try {
                createdPayment = payment.execute(apiContext, paymentExecution);
            }catch (PayPalRESTException e) {
                e.printStackTrace();
            }
        } else {
            DeliveryMethod confirmDelivery = (DeliveryMethod) objects[0];
            Map<ProductInfo, Integer> shoppingBag = (Map<ProductInfo, Integer>) objects[1];
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            double total = 0;
            double tax = 0;

            List<Item> items = new ArrayList<>();
            for (Object o : shoppingBag.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                ProductInfo productInfo = (ProductInfo) pair.getKey();
                int quantity = (int) pair.getValue();

                Item item = new Item();
                double newPrice = productInfo.getUnitPrice().doubleValue() * (100 - productInfo.getDiscount())/100;
                double itemPrice = Double.valueOf(df.format(newPrice * 0.85));
                double itemTax = Double.valueOf(df.format(newPrice * 0.15));
                item.setName(productInfo.getName()).setQuantity(String.valueOf(quantity)).setCurrency("EUR").setPrice(String.valueOf(itemPrice));
                items.add(item);
                total += Double.valueOf(df.format(itemPrice * quantity));
                tax += Double.valueOf(df.format(itemTax * quantity));
            }

            ItemList itemList = new ItemList();
            itemList.setItems(items);

            Details details = new Details();
            details.setShipping(confirmDelivery.getCost().toString());
            details.setSubtotal(df.format(total));
            details.setTax(df.format(tax));

            total += tax + confirmDelivery.getCost().doubleValue();

            req.getSession().setAttribute("orderAmount", df.format(total));

            Amount amount = new Amount();
            amount.setCurrency("EUR");
            // Total must be equal to sum of shipping, tax and subtotal.
            amount.setTotal(df.format(total));
            amount.setDetails(details);

            // A transaction defines the contract of a payment - what is the
            // payment for and who is fulfilling it. Transaction is created with
            // a `Payee` and `Amount` types
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("This is the payment transaction description.");

            transaction.setItemList(itemList);

            // The Payment creation API requires a list of
            // Transaction; add the created `Transaction`
            // to a List
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            // A resource representing a Payer that funds a payment
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            // A Payment Resource; create one using
            // the above types and intent as 'sale'
            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            // Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/fail-payment?guid=" + guid);
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/confirmation?guid=" + guid);
            payment.setRedirectUrls(redirectUrls);

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                createdPayment = payment.create(apiContext);
                //log.info("Created payment with id = " + createdPayment.getId() + " and status = " + createdPayment.getState());
                // ###Payment Approval Url
                Iterator<Links> links = createdPayment.getLinks().iterator();
                while (links.hasNext()) {
                    Links link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        req.setAttribute("redirectURL", link.getHref());
                    }
                }
                map.put(guid, createdPayment.getId());
            } catch (PayPalRESTException e) {
                e.printStackTrace();
            }
        }

        return createdPayment;
    }

    @GetMapping("/cart")
    public String cart(Model model, @ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag){
        log.info(" > CART - GET");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if (userInfo!= null){
            List<ShoppingProduct> shoppingProducts = shoppingProductService.findAllByUserId(userInfo.getId());

            Map<ProductInfo,Integer> userShoppingBag = new HashMap<>();
            for(ShoppingProduct shoppingProduct : shoppingProducts){
               userShoppingBag.put(shoppingProduct.getProductInfo(), shoppingProduct.getQuantity());
            }

            model.addAttribute("shoppingBag", userShoppingBag);
        }
        return "cart";
    }

    @GetMapping("/favorite")
    public String favorite(Model model, @ModelAttribute("favoriteBag") List<ProductInfo> favoriteBag){
        log.info(" > Favorite Product - GET");
        UserInfo userInfo = AppUtil.getCustomerFromSession();

        if(userInfo != null){
            List<FavoriteProduct> userFavoriteBag = favoriteProductService.findAllByUserId(userInfo.getId());



            List<ProductInfo> productInfos = new ArrayList<>();
            for (FavoriteProduct f : userFavoriteBag){
                productInfos.add(f.getProductInfo());
            }

            Iterable<ProductInfo> iterableee = productInfoService.findAllProducts();

            model.addAttribute("favoriteBag",productInfos);
            model.addAttribute("iterableee",iterableee);
        }
        return "favorite";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/product/{productId}")
    public String viewProduct(Model model, @PathVariable int productId){
        ProductInfo productInfo = productInfoService.findProduct(productId);
        model.addAttribute("productInfo", productInfo);

        List<ProductInfo> productInfoList = productInfoService.getSameCategoryProducts(productInfo.getCategoryInfo().getId());
        productInfoList.remove(productInfo);
        model.addAttribute("sameCategoryProducts", productInfoList);
        return "product-details";
    }

    @GetMapping("/confirmation")
    public String confirmation(Model model, HttpServletRequest req, @ModelAttribute("shoppingBag") Map<ProductInfo, Integer> shoppingBag){
        String confirmPayment = (String)req.getSession().getAttribute("confirmPayment");
        String orderAmount = (String)req.getSession().getAttribute("orderAmount");
        model.addAttribute("lastCheckProducts", shoppingBag);
        OrderInfo orderInfo = new OrderInfo();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if(confirmPayment == null || orderAmount == null)
            return "redirect:/";

        UserInfo userInfo = userInfoService.findById(AppUtil.getCustomerFromSession().getId());
        model.addAttribute("paidMethod", confirmPayment);

        List<ShoppingProduct> shoppingProductList = shoppingProductService.findAllByUserId(userInfo.getId());
        List<OrderDetail> orderDetailList = new ArrayList<>();

        if("pay-online".equals(confirmPayment)){
            Payment createdPayment = executePaypalPayment(req);

            // make change to order and clear shopping bag
            if("completed".equals(createdPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getState())){
                for (ShoppingProduct shoppingProduct : shoppingProductList) {
                    orderDetailList.add(new OrderDetail(orderInfo, shoppingProduct.getProductInfo(), shoppingProduct.getProductInfo().getUnitPrice(), shoppingProduct.getQuantity(), shoppingProduct.getProductInfo().getDiscount()));
                }

                orderInfo.setOrderDate(new Date());
                orderInfo.setTotalPrice(new BigDecimal(orderAmount));
                orderInfo.setUserInfo(userInfo);
                orderInfo.setDeliveryMethod((DeliveryMethod) req.getSession().getAttribute("confirmDelivery"));
                orderInfo.setShippingAddress((ShippingAddress) req.getSession().getAttribute("confirmShipping"));
                orderInfo.setStatus("Pending");
                orderInfo.setOrderDetails(orderDetailList);
                orderInfoService.update(orderInfo);

                req.getSession().removeAttribute("confirmShipping");
                req.getSession().removeAttribute("confirmDelivery");

                // update invoice table
                PaymentInvoice paymentInvoice = new PaymentInvoice(new Date(), new BigDecimal(orderAmount), orderInfo);
                paymentInvoiceService.insert(paymentInvoice);
            }

        } else {
            BigDecimal totalPrice = new BigDecimal(0);

            for (ShoppingProduct shoppingProduct : shoppingProductList) {
                Float productPrice = shoppingProduct.getProductInfo().getUnitPrice().floatValue() * shoppingProduct.getQuantity() * (100 - (float) shoppingProduct.getProductInfo().getDiscount()) / 100;
                totalPrice = totalPrice.add(new BigDecimal(productPrice));
                orderDetailList.add(new OrderDetail(orderInfo, shoppingProduct.getProductInfo(), shoppingProduct.getProductInfo().getUnitPrice(), shoppingProduct.getQuantity(), shoppingProduct.getProductInfo().getDiscount()));
            }

            orderInfo.setOrderDate(new Date());
            orderInfo.setTotalPrice(totalPrice);
            orderInfo.setUserInfo(userInfo);
            orderInfo.setStatus("Pending");
            orderInfo.setOrderDetails(orderDetailList);
            orderInfoService.update(orderInfo);
        }

        int deleteLoopSize = shoppingProductList.size();
        for (int i = 0; i < deleteLoopSize; i++)
            shoppingProductService.delete(shoppingProductList.get(i));

        model.addAttribute("shoppingBag", new HashMap<ProductInfo, Integer>());
        model.addAttribute("justMadeOrder", orderInfoService.findLastRecord(userInfo.getId()));

        req.getSession().removeAttribute("confirmPayment");
        req.getSession().removeAttribute("orderAmount");

        return "confirm";
    }

    @GetMapping("/fail-payment")
    public String failPayment(){
        return "fail-payment";
    }

}
