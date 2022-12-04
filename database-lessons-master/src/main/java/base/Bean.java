package base;

public class Bean {
    private String sData;
    private int intData;

    public Bean() {
    }

    public Bean(String sData) {
        this.sData = sData;
    }

    public String getsData() {
        return sData;
    }

    public void setsData(String sData) {
        this.sData = sData;
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }
}

