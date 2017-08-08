/*********************************************************************************************************
 * Name: Shanini kumari
 * StudentId: 2150728
 * Description: Mobile is a concrete class, extends product class
 * @version 1.0
 ************************************************************************************************************/
package inventory;


public class Mobile extends Product {
	
    private String mobilecarrier;
    private String deviceModel;
    private String uuid;
    private String osName;
    private String memory;
    private String brand;
    private String cpuCore;
//------------------------------------------------------------------------------    
/**
* default constructor
* 
* @param
*
*/
//------------------------------------------------------------------------------        
public Mobile(){

}
//------------------------------------------------------------------------------
/**
* Mobile constructor used for initializing the mobile class variable.
* It calls the product constructor using super for initializing class variable of the product class.
* @param int productId, String itemname,  double itemprice ,
* String mobilecarrier, String deviceModel, String uuid, String osName,
* String memory, String brand, String cpuCore
* @return null
*/	
//------------------------------------------------------------------------------

public Mobile(int productId, String itemname,  double itemprice , String mobilecarrier, String deviceModel, String uuid, String osName, String memory, String brand, String cpuCore){
        super( productId,  itemname,   itemprice);
        this.mobilecarrier = mobilecarrier;
        this.deviceModel = deviceModel;
        this.uuid = uuid;
        this.osName = osName;
        this.memory=memory;
        this.brand=brand;
        this.cpuCore=cpuCore;
}

public String getMobilecarrier() {
        return mobilecarrier;
}

public void setMobilecarrier(String mobilecarrier) {
        this.mobilecarrier = mobilecarrier;
}

public String getDeviceModel() {
        return deviceModel;
}

public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
}

public String getUuid() {
        return uuid;
}

public void setUuid(String uuid) {
        this.uuid = uuid;
}

public String getOsName() {
        return osName;
}

public void setOsName(String osName) {
        this.osName = osName;
}
public String getMemory() {
    return memory;
}

public void setMemory(String memory) {
    this.memory = memory;
}

public String getBrand() {
    return brand;
}

public void setBrand(String brand) {
    this.brand = brand;
}

public String getCpuCore() {
    return cpuCore;
}

public void setCpuCore(String cpuCore) {
    this.cpuCore = cpuCore;
}
//------------------------------------------------------------------------------
 /**
 * Method for converting object to string 
 * 
 * @param null
 *
 */	
//------------------------------------------------------------------------------     
public String toString() {
    String mobileString= "Name: "+ getName()+"\n"
                    +"Price: "+getPrice()+"\n"
                    +"Brand: "+ getBrand() +"\n"
                    +"Operating System: "+getOsName()+"\n"
                    +"CpuCore: "+ getCpuCore()+"\n"
                    +"Memory Size: "+ getMemory() +"\n"
                    +"Device Model: "+ getDeviceModel() +"\n"
                    +"Mobile Carrier: "+ getMobilecarrier() +"\n"
                    ;
    return mobileString;
   }
}
//------------------------------------------------------------------------------
