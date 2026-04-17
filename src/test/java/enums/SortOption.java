package enums;

public enum SortOption {
    NAME_ASC("pd.name-ASC"),
    NAME_DESC("pd.name-DESC"),
    PRICE_ASC("p.price-ASC"),
    PRICE_DESC("p.price-DESC");

    private final String value;

    SortOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
