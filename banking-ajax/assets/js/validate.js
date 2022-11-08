jQuery.validator.addMethod('phone_valid', function (value) {
    var regex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/;
    return value.trim().match(regex);
  });