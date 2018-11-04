# cordova-ravepay
A Cordova plugin to add Rave into your iOS and Android apps

Create a RavePay instance
```
         cordova.plugins.RavePay({
                     "setAmount": 300,
                     "setCountry": "ZA",
                     "setCurrency": "ZAR",
                     "setEmail": "dakaraimshoperi@gmail.com",
                     "setfName": "Dakarai",
                     "setlName": "Mshoperi",
                     "setNarration": "Payment of items",
                     "setPublicKey": "YOUR_PUBLIC_KEY",
                     "setSecretKey": "YOUR_SECRET_KEY",
                     "setTxRef": "SG-102343297-RV05524398595",
                     "acceptAccountPayments": false,
                     "acceptCardPayments": true,
                     "acceptMpesaPayments": false,
                     "acceptGHMobileMoneyPayments": false,
                     "onStagingEnv": true,
                     "allowSaveCardFeature": true
                }, function(res){
            alert('Response --> ' + res);   
         },function(err){
            alert('Error --> ' + res);
         }); 
```
