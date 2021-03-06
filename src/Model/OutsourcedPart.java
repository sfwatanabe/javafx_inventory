package Model;

/**
 * The OutSourcedPart class holds information about parts purchased
 * from outside vendors.
 *
 * @author Sakae Watanabe
 */
public class OutsourcedPart extends Part{

  //===========================================================================
  // Member Variables
  //===========================================================================
  /** The company name from which the part was purchased. */
  private String companyName;

  //===========================================================================
  // Constructor
  //===========================================================================
  /**
   * Constructs a new OutSourcedPart using the specified parameters.
   *
   * @param id The ID number for the OutSourcedPart.
   * @param name The name for the OutSourcedPart.
   * @param price The price for the OutSourcedPart.
   * @param stock Current stock level for the OutSourcedPart.
   * @param min Minimum stock level for the OutSourcedPart.
   * @param max Maximum stock level for the OutSourcedPart.
   * @param companyName The name of the company supplying the OutSourcedPart.
   */
  public OutsourcedPart(int id, String name, double price, int stock, int min, int max,
      String companyName) {
    super(id, name, price, stock, min, max);
    this.companyName = companyName;
  }

  //===========================================================================
  // Getters & Setters
  //===========================================================================
  /**
   * @param companyName The string value to be set for this company name.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * @return The String value of companyName.
   */
  public String getCompanyName() {
    return this.companyName;
  }

}
