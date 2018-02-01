package objectRepo;

import org.openqa.selenium.By;

public class DocumentPageLocator {
	public static By olTag=By.xpath("//ol/@*[contains(name(), '_wex:tag')]");
	public static By liItems=By.xpath("//ol/li");
	public static String paragraph=".//*[text()=";
    public static By table=By.xpath(".//table/thead[not(@class='ui-datatable-thead')]");
    public static By firsttdElement=By.xpath(".//table/thead[not(@class='ui-datatable-thead')]/following-sibling::tbody/tr[1]/td[1]");
    public static By spinnerElmt=By.xpath("//div[@class='block-ui-spinner']");
}
