use jennifer_db;


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE user_info;
TRUNCATE category_info;
TRUNCATE product_info;
TRUNCATE marketing_campaign;
TRUNCATE campaign_product;
TRUNCATE delivery_method;
TRUNCATE shipping_address;
TRUNCATE order_info;
TRUNCATE order_detail;
SET FOREIGN_KEY_CHECKS = 1;


/*
-- Query: SELECT * FROM jennifer_db.category_info
*/
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (1,'Women',NULL,1);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (2,'Men',NULL,2);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (3,'Kids',NULL,3);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (4,'Home',NULL,4);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (5,'Jackets & Coats',1,1);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (6,'Dresses & Jumpsuits',1,2);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (7,'Jeans',1,3);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (8,'Shirts & Blouses',1,4);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (9,'Skirts',1,5);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (10,'Sleepwear',1,6);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (11,'Swimwear',1,7);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (12,'Shoes',1,8);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (13,'Accessories',1,9);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (14,'Sweaters & Cardigans',2,1);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (15,'Shirts',2,2);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (16,'Jeans',2,3);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (17,'Hoodies & Sweatshirts',2,4);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (18,'Jackets & Suits',2,5);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (19,'Accessories',2,6);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (20,'Shoes',2,7);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (21,'Swimwear',2,8);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (22,'Shorts',2,9);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (23,'Pants & Leggings',3,1);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (24,'Dresses & Skirts',3,2);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (25,'Jeans',3,3);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (26,'Shoes',3,4);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (27,'Sweaters & Cardigans',3,5);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (28,'Accessories',3,6);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (29,'Shorts',3,7);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (30,'Swimwear',3,8);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (31,'Shirts & Blouses',3,9);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (32,'Living Room',4,1);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (33,'Bedroom',4,2);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (34,'Kitchen',4,3);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (35,'Bathroom',4,4);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (36,'Pillows',4,5);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (37,'Curtains',4,6);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (38,'Rugs',4,7);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (39,'Bed Linen',4,8);
INSERT INTO `category_info` (`ID`,`NAME`,`CATEGORY_ID`,`PLACE_ORDER`) VALUES (40,'Storage',4,9);


/*
-- Query: SELECT * FROM jennifer_db.product_info
*/
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (1,'Patterned Cushion Cover',4.99,0,'20170227165541-96012.jpg,20170227165541-31465.jpg',50,'Cushion cover in cotton canvas with a printed pattern at front and solid-color at back. Concealed zip.','100% cotton. Machine wash cold\nImported','available',32);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (2,'Glittery Cushion Cover',12.99,0,'20170227195613-24167.jpg,20170227195613-32506.jpg,20170227195613-51637.jpg',40,'Cushion cover in jacquard-weave fabric with glittery threads. Concealed zip.','60% polyester, 25% metallic fiber, 13% cotton, 2% rayon.\nMachine wash cold\nImported','available',32);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (3,'Large Candle in Ceramic Holder',17.99,0,'20170227195902-70719.jpg,20170227195919-23291.jpg,20170227195923-77973.jpg',30,'Candle in a pineapple-shaped ceramic holder with lid. Unscented. Diameter 3 1/2 in., height including lid approx. 6 in. Burn time 30 hours.','80% paraffin, 20% wax.\nImported','available',32);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (4,'Metal Candle Holder',17.99,0,'20170227200213-93546.jpg,20170227200217-42520.jpg',30,'Monkey-shaped candle holder in metal. Diameter of candle cup 0.86 in. Width of candlestick holder 4 in., height 5 in.','100% metal.\nImported','available',32);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (5,'Tall Metal Plant Pot',12.99,0,'20170227200819-68672.jpg,20170227200821-23384.jpg,20170227200821-62283.jpg',30,'Tall metal plant pot with painted exterior. Height 5 1/4 in., diameter 5 1/4 in.','100% metal.\nImported','available',32);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (6,'Washed Linen Duvet Cover Set',129.00,0,'20170227201147-69793.jpg,20170227201149-54036.jpg,20170227201149-81597.jpg',30,'PREMIUM QUALITY.\nKing/queen duvet cover set in washed linen with double-stitched seams at edges. \nDuvet cover fastens at foot end with concealed metal snap fasteners. \nTwo pillowcases. \nThread count 104. \nTumble-drying will help keep linen soft.','100% linen. Machine wash hot\nImported','available',33);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (7,'Crinkled Cotton Bedspread',69.99,0,'20170227202028-63795.jpg,20170227202031-87984.jpg,20170227202031-61324.jpg',35,'Twin bedspread in woven, crinkled cotton fabric.','100% cotton. Machine wash warm\nImported','available',33);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (8,'Washed Linen Pillowcase',17.99,0,'20170227202258-48000.jpg,20170227202301-56027.jpg,20170227202301-50457.jpg',32,'PREMIUM QUALITY. \nWashed linen pillowcase with double-stitched edges. \nTumble drying will help keep linen soft.','100% linen. Machine wash hot\nImported','available',33);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (9,'Washed Cotton Pillowcase',12.99,0,'20170227202453-38539.jpg,20170227202456-59874.jpg,20170227202456-38351.jpg',20,'CONSCIOUS. \nPillowcase in woven organic cotton fabric, washed for an extra soft feel. \nSupplied in matching drawstring fabric bag.','100% cotton. Machine wash hot\nImported','available',33);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (10,'Washed Linen Bathrobe',49.99,0,'20170227202637-73648.jpg,20170227202640-29424.jpg,20170227202640-24899.jpg',20,'PREMIUM QUALITY. \nBathrobe in washed linen with two front pockets and tie belt. Unisex. \nTumble drying will help keep linen soft.','100% linen. Machine wash warm\nImported','available',33);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (11,'Jeans Slim fit',14.99,0,'20170227203007-81674.jpg,20170227203010-59253.jpg',15,'5-pocket jeans in soft, washed stretch denim with an adjustable elasticized waistband, fly with snap fastener, and slim legs.','78% cotton, 20% polyester, 2% spandex. Machine wash warm\nImported','available',23);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (12,'Patterned Joggers',14.99,0,'20170227203112-57252.jpg,20170227203114-40169.jpg',20,'Joggers in soft sweatshirt fabric with a printed pattern. Elastication and decorative tie at waist, side pockets, and sewn cuffs at hems.','80% cotton, 20% polyester. Machine wash warm\nImported','available',23);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (13,'2-pack Tops',14.99,0,'20170227204313-51651.jpg,20170227204315-18784.jpg',20,'Long-sleeved tops in soft cotton slub jersey with snap fasteners on one shoulder.\nOne top with printed motif at front and one with printed pattern.','100% cotton. Machine wash warm\nImported','available',23);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (14,'Pull-on Pants',14.99,0,'20170227204635-44029.jpg,20170227204637-88011.jpg',13,'Pull-on pants in soft, washed denim with a faded pattern.\nElasticized waistband with decorative ties at front, heart-shaped appliqués at knees, mock front pockets, and elasticized hems.','100% cotton. Machine wash warm\nImported','available',23);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (15,'Dress and Leggings',17.99,0,'20170227205859-44891.jpg,20170227205901-35065.jpg',23,'Set with a dress and leggings in a soft cotton blend.\nDress in sweatshirt fabric with a printed motif at front, long sleeves with sewn cuffs, and drawstring at waist.\nLeggings in melange jersey with elasticized waistband.','60% cotton, 40% polyester. Machine wash warm\nImported','available',23);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (16,'Sweatshirt Dress',14.99,0,'20170227210933-81404.jpg,20170227210935-67133.jpg',20,'Dress in sweatshirt fabric with a printed motif and appliqués at front.\nSnap fasteners at back of neck, short sleeves with sewn cuffs, and front pockets.','80% cotton, 20% polyester. Machine wash warm\nImported','available',24);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (17,'Patterned Dress',12.99,0,'20170227211853-48518.jpg,20170227211856-97747.jpg',12,'Sleeveless dress in soft woven fabric with a gathered seam at top and buttons at back of neck. Unlined.','100% cotton.\nImported','available',24);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (18,'Cotton Dress',9.99,0,'20170227212312-76032.jpg,20170227212314-60408.jpg',23,'CONSCIOUS. \nPatterned dress in airy, woven organic cotton fabric. \nGathered seam and decorative chiffon bow at top, and buttons at back. Unlined.','100% cotton. Machine wash warm\nImported','available',24);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (19,'3-piece Jersey Set',14.99,0,'20170227212556-49298.jpg,20170227212558-82004.jpg',12,'Set in soft cotton jersey. \nDress with a printed design, patch front pocket, concealed snap fastener on one shoulder, and short sleeves with sewn cuffs. \nLeggings with elasticized waistband. \nElasticized hairband with attached bow.','95% cotton, 5% spandex. Machine wash warm\nImported','available',24);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (20,'Cotton Piqué Top',6.99,0,'20170227213207-17240.jpg,20170227213210-82785.jpg',25,'Top in cotton piqué with a printed pattern. \nEmbroidered detail at front, rounded collar, button placket, and puff sleeves.','95% cotton, 5% spandex. Machine wash warm\nImported','available',24);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (21,'Fine-knit Sweater',29.99,0,'20170227234338-91318.jpg,20170227234341-67310.jpg,20170227234341-67173.jpg',26,'Fine-knit sweater in a cotton blend. Dropped shoulders, long sleeves, rolled edges at neckline and cuffs, and short slits at sides. Slightly longer at back.','72% cotton, 24% acrylic, 4% polyester.\nImported','available',14);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (22,'Fine-knit Sweater',24.99,0,'20170227235107-96269.jpg,20170227235110-59888.jpg,20170227235110-24396.jpg',32,'Fine-knit sweater in slub cotton yarn with long raglan sleeves and ribbing at neckline, cuffs, and hem.','100% cotton. Machine wash warm\nImported','available',14);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (23,'Trashed Cardigan',49.99,0,'20170227235859-73120.jpg,20170227235903-29633.jpg,20170227235903-93909.jpg',22,'Long-sleeved cardigan in double layers of jersey with heavily distressed details, draped lapels, and side pockets. No buttons.','70% polyester, 25% cotton, 5% spandex. Machine wash cold\nImported','available',14);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (24,'Textured-knit Cotton Sweater',34.99,0,'20170228000037-20529.jpg,20170228000040-18100.jpg,20170228000040-78606.jpg',15,'Long-sleeved cotton sweater in a textured knit with ribbing at cuffs and hem.','100% cotton. Machine wash cold\nImported','available',14);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (25,'Fine-knit Sweater',24.99,0,'20170228000222-79683.jpg,20170228000225-13831.jpg,20170228000225-42680.jpg',36,'Fine-knit sweater in slub cotton yarn with long raglan sleeves and ribbing at neckline, cuffs, and hem.','100% cotton. Machine wash warm\nImported','available',14);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (26,'Oxford Shirt Regular fit',24.99,0,'20170228000428-77607.jpg,20170228000432-46564.jpg,20170228000432-92427.jpg',22,'Long-sleeved shirt in oxford-weave cotton fabric with a button-down collar, chest pocket, and yoke at back with locker loop. Regular fit - a classic fit with good freedom of movement and a gently shaped waist for a comfortable, tailored silhouette.','100% cotton. Machine wash warm\nImported','available',15);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (27,'Cotton Shirt Regular fit',24.99,0,'20170228000609-28937.jpg,20170228000612-98170.jpg,20170228000612-28786.jpg',34,'Long-sleeved shirt in woven cotton fabric. Button-down collar, chest pocket, and yoke with locker loop at back. Regular fit.','100% cotton. Machine wash cold\nImported','available',15);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (28,'Cotton Shirt Regular fit',24.99,0,'20170228000730-77348.jpg,20170228000733-28006.jpg,20170228000733-75377.jpg',16,'Long-sleeved shirt in woven cotton fabric. Button-down collar, chest pocket, and yoke with locker loop at back. Regular fit.','100% cotton. Machine wash cold\nImported','available',15);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (29,'Denim Shirt',29.99,0,'20170228000916-73383.jpg,20170228000919-81702.jpg,20170228000919-87903.jpg',22,'Straight-cut denim shirt with metal snap fasteners at front, sleeves with cuffs, and chest pockets with flap, concealed snap fastener, and pleat.','100% cotton. Machine wash warm\nImported','available',15);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (30,'Cotton Shirt Slim fit',29.99,0,'20170228001044-74380.jpg,20170228001048-76552.jpg,20170228001048-57875.jpg',30,'Long-sleeved shirt in woven cotton fabric with a turn-down collar. Yoke at back, concealed buttons at front, and buttons at cuffs. Slim fit – accentuated waist and narrow shoulders. Tailored cut for a close-fitting silhouette.','100% cotton. Machine wash cold\nImported','available',15);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (31,'Bomber Jacket',39.99,0,'20170228001252-55170.jpg,20170228001255-93294.jpg,20170228001255-34206.jpg',21,'Lightly padded, slightly longer bomber jacket in a generous fit. Zip at front, front pockets with flap, and sleeve pocket with zip. Ribbing at neckline, cuffs, and hem. Lined.','100% polyester. Machine wash warm\nImported','available',5);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (32,'Padded Bomber Jacket',39.99,0,'20170228001436-41518.jpg,20170228001439-13606.jpg,20170228001439-58088.jpg',37,'Lightly padded bomber jacket in satin. Zip at front, front pockets with flap and snap fastener, and ribbing at neckline, cuffs, and hem.','100% polyester. Machine wash warm\nImported','available',5);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (33,'Short Coat',49.99,0,'20170228001623-57437.jpg,20170228001627-76775.jpg,20170228001627-98562.jpg',12,'Short coat in thick, woven fabric with no buttons. Patch pockets with flap. Lined.','95% polyester, 5% spandex. Machine wash cold\nImported','available',5);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (34,'Short Trenchcoat',69.99,0,'20170228001748-53320.jpg,20170228001751-82199.jpg,20170228001751-95605.jpg',16,'Short, double-breasted trenchcoat in woven cotton-blend fabric with shoulder tabs. Semi-attached yoke at back, front pockets with flap, and adjustable tabs at cuffs. Vent at back. Lined.','65% polyester, 35% cotton. Machine wash cold\nImported','available',5);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (35,'Felted Coat',59.99,0,'20170228001917-21479.jpg,20170228001920-28751.jpg,20170228001920-89645.jpg',19,'Coat in soft. felted fabric with notched lapels, concealed buttons at front, and front pockets with flap. Unlined.','87% polyester, 13% rayon. Machine wash warm\nImported','available',5);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (36,'Wrap Dress',39.99,0,'20170228002106-22530.jpg,20170228002108-13766.jpg,20170228002108-89813.jpg',34,'Short dress in airy chiffon with a attached wrapover front. Flounce-trimmed V-neck, elasticized seam at waist, elasticized, flounced cuffs, and flounced hem. Lined.','100% polyester. Machine wash warm\nImported','available',6);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (37,'Fitted Dress',39.99,0,'20170228002236-79063.jpg,20170228002240-52534.jpg,20170228002240-49702.jpg',12,'Short, fitted halterneck dress in thick jersey. Narrow-cut at top with fastening at back of neck and low-cut back with narrow straps and airy side panels. Lined.','60% rayon, 35% nylon, 5% spandex. Machine wash cold\nImported','available',6);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (38,'Lace Dress',39.99,0,'20170228002353-16530.jpg,20170228002356-55246.jpg,20170228002359-72966.jpg',42,'Fitted dress in lace. V-neck at front and back, long sleeves, and seam at waist. Lined.','92% nylon, 8% spandex. Machine wash cold\nImported','available',6);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (39,'Ribbed Jersey Dress',24.99,0,'20170228002521-59707.jpg,20170228002524-26445.jpg,20170228002524-50276.jpg',16,'Short dress in ribbed jersey. Opening at back of neck with covered button. 3/4-length sleeves and flared skirt.','95% polyester, 5% spandex. Machine wash warm\nImported','available',6);
INSERT INTO `product_info` (`ID`,`NAME`,`UNIT_PRICE`,`DISCOUNT`,`IMAGE`,`QUANTITY`,`DESCRIPTION`,`DETAIL`,`STATUS`,`CATEGORY_ID`) VALUES (40,'Black Lace Dress',39.99,0,'20170228002714-58716.jpg,20170228002716-19522.jpg,20170228002719-50917.jpg',38,'Fitted dress in lace. V-neck at front and back, long sleeves, and seam at waist. Lined.','92% nylon, 8% spandex. Machine wash cold\nImported','available',6);


/*
-- Query: SELECT * FROM jennifer_db.marketing_campaign
*/
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (1,'Spring Sale 2016','20170325212757-13351.jpg','2016-02-08','2016-02-21','concluded');
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (2,'Summer Sale 2016','20170325212750-43770.jpg','2016-05-02','2016-05-29','concluded');
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (3,'Autumn Sale 2016','20170325212742-84994.jpg','2016-09-01','2016-09-18','concluded');
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (4,'Winnter Sale 2016','20170325212736-77447.jpg','2016-12-12','2030-12-25','running');
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (5,'Spring Sale 2017','20170325212725-33306.jpg','2017-03-06','2030-03-26','running');
INSERT INTO `marketing_campaign` (`ID`,`EVENT`,`BANNER`,`START_DATE`,`END_DATE`,`STATUS`) VALUES (6,'Furniture Sale 2017','20170325212712-60383.jpg','2017-03-06','2030-04-30','running');


/*
-- Query: SELECT * FROM jennifer_db.campaign_product
*/
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (1,6,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (2,6,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (4,6,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (5,6,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (6,6,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (7,6,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (8,6,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (10,6,12);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (11,3,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (11,4,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (11,5,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (13,5,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (15,2,12);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (15,4,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (15,5,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (17,1,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (17,3,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (17,4,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (18,5,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (20,1,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (21,1,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (21,3,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (21,5,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (22,4,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (24,2,20);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (25,4,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (26,2,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (26,5,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (28,4,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (28,5,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (29,1,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (30,2,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (31,3,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (31,5,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (32,4,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (32,5,10);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (34,1,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (35,2,20);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (36,3,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (37,5,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (38,1,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (38,2,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (39,2,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (39,3,5);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (39,5,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (40,1,20);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (40,3,15);
INSERT INTO `campaign_product` (`PRODUCT_ID`,`CAMPAIGN_ID`,`DISCOUNT`) VALUES (40,5,20);


/*
-- Query: SELECT * FROM jennifer_db.user_info
LIMIT 0, 1000
*/
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (1,'Manager Hien','thuhien@gmail.com','$2a$10$07cQY1uFkjcJ7uOVUW6w.OGUD8aYM3xLaU9KXscob1MA68enIy94e','MANAGER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (2,'Lily Nguyen','lily31392@gmail.com','$2a$10$RJ1VIcVglQiuo37IlJQ75ONZtt2A0b5kT1ESdYfbaLbTun2p7L66i','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (3,'Ho Hoang Phuc','jamesho287@gmail.com','$2a$10$bXXGpbzUTXcjA4nCkbcoN.nJE6tE7GriX2o29rVzMyef11xjkjUxS','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (4,'Manager Phuc','manager@jennifer.com','$2a$10$IpllokLKjOcohU1LFxiiJ.TZkBre5b0.SrnuywUGT6vkbHM9ZPHyu','MANAGER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (5,'Alex Max','alex@gmail.com','$2a$10$z2VZQhnNCLD4jXXzRZrTAuqLvs7WYG0bXUFRRar38lNH7eBxpeiXG','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (6,'Larry Pages','larry@gmail.com','$2a$10$kGxC/O.BBfBe5xU0MkJeKOr2ASl3LJ/9vGC7BRINCNydNe1Rt0SJS','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (7,'Tim Cook','tim@apple.com','$2a$10$wH5Nj/Q/Yn9WPiscFJ7q5ei1cHJhZ2hi6rhFID8hiQps/I6lmcDxW','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (8,'Jennifer Lawrence','jennifer@apple.com','$2a$10$IUC6cISvTJQR3Wr3bO5KQOOmnWht1XoDr1hm87Qcz4oDVYrrRb7JC','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (9,'Brad Pitt','brad@gamil.com','$2a$10$hDjf49UJyrpza.LF/8X6YuJn1G5GlqgSTFJ46rjadaj0Mqb4sjjOy','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (10,'Jackie Chan','jackie@gmail.com','$2a$10$MJJrMM57Fb4y9I3LPO9Xa.jkehzsoMsKCDZ3sG.HllSSxfRBLZ18u','CUSTOMER');
INSERT INTO `user_info` (`ID`,`FULLNAME`,`EMAIL`,`PASSWORD`,`ROLE`) VALUES (11,'Harry Potter','harry@gmail.com','$2a$10$Jzh4ey0VXyN4P0Sbr539Wu4IcvADSY4VtqkqawPCiSt.mummwXjPS','CUSTOMER');


/*
-- Query: SELECT * FROM jennifer_db.delivery_method
*/
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (1,'Domestic Standard',3.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (2,'Domestic Expedited',6.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (3,'Domestic Two-Day',14.95,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (4,'Domestic One-Day',24.99,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (5,'International Standard',14.95,'2017-02-01','2017-12-31');
INSERT INTO `delivery_method` (`ID`,`NAME`,`COST`,`START_DATE`,`END_DATE`) VALUES (6,'International Expedited',46.95,'2017-02-01','2017-12-31');


/*
-- Query: SELECT * FROM jennifer_db.shipping_address
*/
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (1,'Manager Hien','Olympiakatu 10 A11','Vaasa','65100','Available',1);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (2,'Lily Nguyen','Konepajakatu 12 P27','Vaasa','65100','Available',2);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (3,'Ho Hoang Phuc','Wolffintie 30','Vaasa','65200','Available',3);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (4,'Manager Phuc','Tekla 16','Vaasa','65500','Available',4);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (5,'Alex Max','Rastaankuja 20','Vaasa','65100','Available',5);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (6,'Larry Pages','Myllykatu 16 L32','Vaasa','65100','Available',6);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (7,'Tim Cook','New Yok 16','New York','644672','Available',7);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (8,'Jennifer Lawrence','California 452','California City','645435','Available',8);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (9,'Brad Pitt','Los Angeles LA543','Los Angeles ','987643','Available',9);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (10,'Jackie Chan','Shanghai 34 K89','Shanghai','651200','Available',10);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (11,'Harry Potter','London 16 L32','London','234467','Available',11);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (12,'Manager Hien','Olympiakatu 12 B48','Vaasa','65100','Available',1);
INSERT INTO `shipping_address` (`ID`,`RECIPIENT`,`ADDRESS`,`CITY`,`POSTAL_CODE`,`STATUS`,`USER_ID`) VALUES (13,'Lily Nguyen','Ida Aalbergin ','Helsinki','00400','Available',2);


/*
-- Query: SELECT * FROM jennifer_db.order_info
*/
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (1,'2017-02-15 00:00:00',82.98,2,2,1,'Done');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (2,'2017-03-01 00:00:00',42.00,3,3,2,'Pending');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (3,'2017-02-15 00:00:00',49.95,5,4,3,'Processed');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (4,'2017-03-02 00:00:00',49.98,6,5,5,'Processing');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (5,'2017-02-28 00:00:00',35.95,7,7,5,'Processed');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (6,'2017-03-02 00:00:00',52.50,8,8,3,'Pending');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (7,'2017-02-12 00:00:00',23.50,9,9,4,'Processed');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (8,'2017-02-24 00:00:00',35.60,10,10,6,'Done');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (9,'2017-02-19 00:00:00',45.60,11,11,6,'Done');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (10,'2017-02-27 00:00:00',53.50,2,2,1,'Processed');
INSERT INTO `order_info` (`ID`,`ORDER_DATE`,`TOTAL_PRICE`,`USER_ID`,`SHIPPING_ADDRESS_ID`,`DELIVERY_ID`,`STATUS`) VALUES (11,'2017-02-05 00:00:00',46.60,3,3,2,'Done');


/*
-- Query: SELECT * FROM jennifer_db.order_detail
*/
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (1,23,49.99,1,20);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (1,32,39.99,1,10);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (2,14,14.99,2,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (2,18,9.99,2,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (3,39,24.99,1,0);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (4,10,49.99,1,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (5,7,69.99,1,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (6,16,14.99,3,20);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (7,20,6.99,2,10);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (8,25,24.99,1,10);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (9,30,29.99,2,20);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (9,33,49.99,1,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (10,31,39.99,2,30);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (11,14,14.99,1,10);
INSERT INTO `order_detail` (`ORDER_ID`,`PRODUCT_ID`,`UNIT_PRICE`,`QUANTITY`,`APPLIED_DISCOUNT`) VALUES (11,33,49.99,1,30);