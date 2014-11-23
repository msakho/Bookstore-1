package edu.luc.fall2014.comp433.project.rest.representation;

import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.luc.fall2014.comp433.project.model.Customer;

public class CustomerRepresentation extends BaseRepresentation {

	private BookstoreURI uri;
	private Short id;
	private String login;
	private String password;
	private String name;
	private List<AddressRepresentation> addresses;
	private List<OrderRepresentation> orders;

	public CustomerRepresentation(Customer entity, BookstoreURI uri) {
		super();
		this.uri = uri;
		populateFields(entity, uri);
	}

	public void addAddressesLinks(List<Short> addressesId) {
		for (Short id : addressesId) {
			addLink(new Link("address", uri.getAddressPath(id.toString()),
					HttpMethod.GET, MediaType.APPLICATION_JSON));
		}
	}

	public void addOrdersLinks(List<Short> ordersId) {
		for (Short id : ordersId) {
			addLink(new Link("order", uri.getOrderPath(id.toString()),
					HttpMethod.GET, MediaType.APPLICATION_JSON));
		}
	}

	private void populateFields(Customer entity, BookstoreURI uri) {
		if (null != entity) {
			this.setId(entity.getId());
			this.setName(entity.getName());
			this.setLogin(entity.getLogin());
			createLinks(uri);
		}
	}

	private void createLinks(BookstoreURI uri) {
		// TODO review links
		addLink(new Link("self", uri.getCustomerPath(getLogin()),
				HttpMethod.GET, MediaType.APPLICATION_JSON));
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@JsonIgnore
	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@XmlTransient
	public List<AddressRepresentation> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRepresentation> addresses) {
		this.addresses = addresses;
	}

	@JsonIgnore
	@XmlTransient
	public List<OrderRepresentation> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderRepresentation> orders) {
		this.orders = orders;
	}

}