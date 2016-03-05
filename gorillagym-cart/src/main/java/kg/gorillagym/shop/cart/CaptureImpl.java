package kg.gorillagym.shop.cart;

import ru.egalvi.shop.Capture;

public class CaptureImpl implements Capture {
    private String token;
    private String imagehash;
    private byte[] imagedata;

    public CaptureImpl() {
    }

    public CaptureImpl(String token, String imagehash, byte[] imagedata) {
        this.token = token;
        this.imagehash = imagehash;
        this.imagedata = imagedata;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImagehash() {
        return imagehash;
    }

    public void setImagehash(String imagehash) {
        this.imagehash = imagehash;
    }

    public byte[] getImagedata() {
        return imagedata;
    }

    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

    @Override
    public String toString() {
        return "CaptureImpl{" +
                "token='" + token + '\'' +
                ", imagehash='" + imagehash + '\'' +
                '}';
    }
}
