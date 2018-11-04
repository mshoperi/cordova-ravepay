var exec = require('cordova/exec');

exports.RavePay = function (arg0, success, error) {
    exec(success, error, 'RavePaycall', 'pay', [arg0]);
};
