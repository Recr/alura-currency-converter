public class Conversion {
    private String result;
    private String timeLastUpdateUtc;
    private String baseCode;
    private String targetCode;
    private String conversionRate;
    private String conversionResult;

    public Conversion (ConversionDto conversionDto) {
        this.result = conversionDto.result();
        this.timeLastUpdateUtc = conversionDto.timeLastUpdateUtc();
        this.baseCode = conversionDto.baseCode();
        this.targetCode = conversionDto.targetCode();
        this.conversionRate = conversionDto.conversionRate();
        this.conversionResult = conversionDto.conversionResult();
    }

    public String getResult() {
        return result;
    }

    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public String getConversionResult() {
        return conversionResult;
    }

    @Override
    public String toString() {
        return  "Request state: " + getResult() + "\n\n" +
                "Time: " + getTimeLastUpdateUtc() + "\n" +
                "Base Currency: " + getBaseCode() + "\n" +
                "Target Currency: " + getTargetCode() + "\n" +
                "Conversion rate:" + getConversionRate() + "\n" +
                "Result: " + getConversionResult() + "\n\n";
    }
}
