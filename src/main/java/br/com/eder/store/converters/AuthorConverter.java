package br.com.eder.store.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.eder.store.models.Author;

@FacesConverter("autorConverter") // In order to reference it only where we want!
public class AuthorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String id) {
		
		if(id == null || id.trim().isEmpty()) {
			return null;
		}
		System.out.println("Converting to Object: " + id);
		
		Author author = new Author();
		author.setId(Integer.valueOf(id));
		return author;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object authorObject) {
		if (authorObject == null) {
			return null;
		}
		System.out.println("Converting to String: " + authorObject);
		Author author = (Author) authorObject;
		return author.getId().toString();
	}

}
