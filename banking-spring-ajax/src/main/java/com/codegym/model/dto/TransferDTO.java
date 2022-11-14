//package com.codegym.model.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class TransferDTO implements Validator {
//    private long id;
//    @NotEmpty(message = "ID người gửi không được để trống")
//    @Pattern(regexp = "^\\d+$", message = "ID người gửi phải là số")
//    private long senderId;
//
//    @NotEmpty(message = "ID người nhận không được để trống")
//    @Pattern(regexp = "^\\d+$", message = "ID người nhận phải là số")
//    private long recipientId;
//
//    @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số")
//    private String transferAmount;
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return TransferDTO.class.isAssignableFrom(aClass);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        TransferDTO transferDTO = (TransferDTO) target;
//
//        String transferAmount = transferDTO.getTransferAmount();
//
//        if (transferAmount != null && transferAmount.length() > 0) {
//            if (transferAmount.length() > 9){
//                errors.rejectValue("transferAmount", "transferAmount.max", "Số tiền chuyển khoản tối đa là 1.000.000");
//                return;
//            }
//
//            if (!transferAmount.matches("(^$|[0-9]*$)")){
//                errors.rejectValue("transferAmount", "transferAmount.number", "Chỉ chấp nhận số tiền chuyển khoản là ký tự số");
//                return;
//            }
//
//            float transactionAmountFloat= Float.parseFloat(transferAmount);
//
//            if (transactionAmountFloat % 10 > 0) {
//                errors.rejectValue("transferAmount", "transferAmount.decimal", "Số tiền chuyển khoản phải là số chẵn chia hết cho 10");
//            }
//
//        } else {
//            errors.rejectValue("transferAmount",  "transferAmount.null", "Số tiền chuyển khoản là bắt buộc");
//        }
//    }
//}
