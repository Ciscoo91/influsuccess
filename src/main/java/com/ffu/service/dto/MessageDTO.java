package com.ffu.service.dto;

import com.ffu.domain.enumeration.MessageStatus;

import javax.validation.constraints.NotBlank;

public class MessageDTO {

    private Long id;
    @NotBlank
    private MessageStatus status;
    @NotBlank
    private String content;
    @NotBlank
    private Long senderId;
    @NotBlank
    private Long receiverId;
    private String senderLogin;
    private String receiverLogin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public String getReceiverLogin() {
        return receiverLogin;
    }

    public void setReceiverLogin(String receiverLogin) {
        this.receiverLogin = receiverLogin;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + id +
            ", status=" + status +
            ", content='" + content + '\'' +
            ", idSender=" + senderId +
            ", idReceiver=" + receiverId +
            ", loginSender='" + senderLogin + '\'' +
            ", loginReceiver='" + receiverLogin + '\'' +
            '}';
    }
}
