package ru.dreamkas.webmoney.objects.base;

public enum ServiceType {
    TEST(1),
    PRODUCTION(5);

    private int type;

    ServiceType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
