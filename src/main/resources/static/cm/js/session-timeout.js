$( document ).ready(function() {
    /**
     * Monitor the session timeout cookie from Apache and log the user out when expired
     */
    "use strict";

    checkSession();

    /**
     * We can't assume the server time and client time are the same
     * so lets calculate the difference
     */
    (function calcOffsetcalcOffset(){
        var serverTime = $.cookie('serverTime');
        serverTime = serverTime == null ? null : Math.abs(serverTime);
        var clientTimeOffset = (new Date()).getTime() - serverTime;
        $.cookie('clientTimeOffset', clientTimeOffset);
    })();

    /**
     * Check the sessionExpiry cookie and see if we should send the user to /
     */
    function checkSession(){
        var sessionExpiry = Math.abs($.cookie('sessionExpiry'));
        var timeOffset = Math.abs($.cookie('clientTimeOffset'));
        var localTime = (new Date()).getTime();
        if (!sessionExpiry) {
            window.console.log("Unknown session sessionExpiry");
            return;
        }
        if (localTime - timeOffset > (sessionExpiry + 15000)) { // 15 extra seconds to make sure
            window.location = "/login";
            $.removeCookie('sessionExpiry');
        } else {
            setTimeout('checkSession()', 10000);
        }
        //print out message
        //window.console.log("Session expires in " + ((sessionExpiry + 15000) - localTime - timeOffset) + "ms");
    };

    window.checkSession = checkSession; //Used for recalling via setTimeout

});
