package com.bigshark.budejie_mvp.bean;

/**
 * Created by bigShark on 2017/1/3.
 */

public class PhoneRecharge {
    private String reason;
    private int errorCode;
    private Result result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    class Result{
        private String cardid;
        private String cardnum;
        private String ordercash;
        private String cardname;
        private String sporderId;
        private String uorderid;
        private String gameUserid;
        private String gameState;

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getOrdercash() {
            return ordercash;
        }

        public void setOrdercash(String ordercash) {
            this.ordercash = ordercash;
        }

        public String getCardname() {
            return cardname;
        }

        public void setCardname(String cardname) {
            this.cardname = cardname;
        }

        public String getSpordeId() {
            return sporderId;
        }

        public void setSporderId(String sporderId) {
            this.sporderId = sporderId;
        }

        public String getUorderid() {
            return uorderid;
        }

        public void setUorderid(String uorderid) {
            this.uorderid = uorderid;
        }

        public String getGameUserid() {
            return gameUserid;
        }

        public void setGameUserid(String gameUserid) {
            this.gameUserid = gameUserid;
        }

        public String getGameState() {
            return gameState;
        }

        public void setGameState(String game_state) {
            this.gameState = game_state;
        }
    }
}
