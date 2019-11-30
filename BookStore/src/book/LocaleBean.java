package book;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class LocaleBean implements Serializable {

	private final static Locale ENGLISH = new Locale("en");
	private final static Locale TURKISH = new Locale("tr");
	private Locale currentLocale = ENGLISH;

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public String setEnglish() {
		currentLocale = ENGLISH;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
		return null;
	}

	public String setTurkish() {
		currentLocale = TURKISH;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
		return null;
	}

}
