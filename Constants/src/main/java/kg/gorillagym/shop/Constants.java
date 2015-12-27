package kg.gorillagym.shop;

public final class Constants {

    public static final String URL = "http://100pudov.kg";
    public static final String CATEGORIES = "/api/categories";
    public static final String PRODUCTS = "/api/products";
    public static final String SUBMIT_ORDER = "/api/submit_order";
    public static final String CAPTURE = "/api/capture";

    private Constants() {
        throw new AssertionError();
    }
}
