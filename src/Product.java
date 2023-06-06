import java.util.ArrayList;

public class Product {
    
    private String type;
    private String name;
    private String size;
    private String color;
    private String additionalInfo;

    public Product(String type, String name, String size, String color){
        this.type = type;
        this.name = name;
        this. size = size;
        this.color = color;
    }

    
    public Product(String type, String name, String size, String color, String additionalinfo){
        this.type = type;
        this.name = name;
        this. size = size;
        this.color = color;
        this. additionalInfo = additionalinfo;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
      
        ArrayList<String> toPrint = new ArrayList<>();
        
        toPrint.add(name);
        if (null != size && !size.equals("")) {
            toPrint.add(size);
        }
        if (null != color && !color.equals("") ) {
            toPrint.add(color);
        }
        if (null != additionalInfo && !additionalInfo.equals("")) {
            toPrint.add(additionalInfo);
        }

        StringBuilder strbuilder = new StringBuilder(name);
        for (int i = 1; i < toPrint.size();i++){
            strbuilder.append(", " + toPrint.get(i));
        }
        
        return strbuilder.toString();
    }
}




