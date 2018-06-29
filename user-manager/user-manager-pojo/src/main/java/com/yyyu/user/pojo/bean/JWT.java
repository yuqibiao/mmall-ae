package com.yyyu.user.pojo.bean;

/**
 * 功能：JWT令牌
 *
 * @author yu
 * @version 1.0
 * @date 2018/6/29
 */
public class JWT {

    /**
     * 荷载信息
     *
     */
    public static class Payload{
        private String iss;//签发者
        private Long exp ;//过期时间
        private Long sub ;//用户Id
        private String aud;//接收方
        private Long nbf;//这个时间之前token不可用
        private Long jat;//签发时间
        private String jti;//令牌标识
        private String claim;//访问主张

        public String getIss() {
            return iss;
        }

        public void setIss(String iss) {
            this.iss = iss;
        }

        public Long getExp() {
            return exp;
        }

        public void setExp(Long exp) {
            this.exp = exp;
        }

        public Long getSub() {
            return sub;
        }

        public void setSub(Long sub) {
            this.sub = sub;
        }

        public String getAud() {
            return aud;
        }

        public void setAud(String aud) {
            this.aud = aud;
        }

        public Long getNbf() {
            return nbf;
        }

        public void setNbf(Long nbf) {
            this.nbf = nbf;
        }

        public Long getJat() {
            return jat;
        }

        public void setJat(Long jat) {
            this.jat = jat;
        }

        public String getJti() {
            return jti;
        }

        public void setJti(String jti) {
            this.jti = jti;
        }

        public String getClaim() {
            return claim;
        }

        public void setClaim(String claim) {
            this.claim = claim;
        }
    }

    /**
     * 头部信息
     */
    public static class Header{
        private String alg; //签名摘要算法
        private String tyyp;//token类型

        public Header(String alg, String tyyp) {
            this.alg = alg;
            this.tyyp = tyyp;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public String getTyyp() {
            return tyyp;
        }

        public void setTyyp(String tyyp) {
            this.tyyp = tyyp;
        }
    }

}
