package cn.fk.common.model.vo;

public class DataBox {

    private String status;
    private String message;
    private Object data;
    private int dataCount;

    public DataBox() {
    }

    public DataBox(String status, Object data) {
        super();
        this.status = status;
        this.data = data;
    }

    public DataBox(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
    public DataBox(String status, Object data, int dataCount) {
        super();
        this.status = status;
        this.data = data;
        this.dataCount = dataCount;
    }

    public DataBox(String status, String message, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public DataBox(String status, String message, Object data, int dataCount) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.dataCount = dataCount;
    }


    public DataBox(String message) {
        super();
        this.message = message;
    }

    public static DataBox newInstance() {
        return new DataBox();
    }

    public static DataBox newInstance(String status, String message) {
        return new DataBox(status, message);
    }

    public static DataBox newInstance(String status, Object data) {
        return new DataBox(status, data);
    }

    public static DataBox newInstance(String status, Object data, int dataCount) {
        return new DataBox(status, data,dataCount);
    }

    public static DataBox newInstance(String status, String message , Object data) {
        return new DataBox(status,message, data);
    }

    public static DataBox newInstance(String status, String message , Object data,int dataCount) {
        return new DataBox(status,message, data,dataCount);
    }

    public static DataBox newInstance(String message) {
        return new DataBox(message);
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

}
