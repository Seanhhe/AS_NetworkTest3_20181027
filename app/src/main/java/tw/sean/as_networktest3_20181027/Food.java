package tw.sean.as_networktest3_20181027;

public class Food {
    private String name, address, tel, hostWords, feature, coordinate, picurl;

    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setTel(String tel){this.tel = tel;}
    public void sethostWords(String hostWords){this.hostWords = hostWords;}
    public void setfeature(String feature){this.feature = feature;}
    public void setcoordinate(String coordinate){this.coordinate = coordinate;}
    public void setpicurl(String picurl){this.picurl = picurl;}

    public String getName(){return name;}
    public String getAddress(){return address;}
    public String getTel(){return tel;}
    public String gethostWords(){return hostWords;}
    public String getfeature(){return feature;}
    public String getcoordinate(){return coordinate;}
    public String geticurl(){return picurl;}

}
