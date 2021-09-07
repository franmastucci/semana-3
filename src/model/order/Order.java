package model.order;

import model.provider.Product;
import model.user.User;

public class Order {
	private long orderNumber;
	private User customer;
	private Product product;
	private Integer quantity;
	private OrderState state;
	
	OrderState pendingState = new PendingState();
	OrderState preparedState = new PreparedState();
	OrderState arrivedState = new ArrivedState();
	OrderState cancelState = new CancelState();

	private Order() {}

	//Constructor creado para cumplir con requerimientos de Hibernate	
	private Order(long anOrderNumber, User aCustomer, Product aProduct, Integer aQuantity, OrderState anOrderState) {
		orderNumber = anOrderNumber;
		customer = aCustomer;
		product = aProduct;
		quantity = aQuantity;
		state = anOrderState;
	}

	private Order(User aCustomer, Product aProduct, Integer aQuantity) {
		customer = aCustomer;
		product = aProduct;
		quantity = aQuantity;
		state = this.getPendingState();
	}

	//Metodo de acceso publico para crear instancias de Order	
	public static Order newOrder(User aCustomer, Product aProduct, Integer aQuantity) {
		return new Order(aCustomer, aProduct, aQuantity);
	}
	
	public Float getTotalCost() {
        return this.getProduct().getPrice().getValue() * this.getQuantity();
    }
	
	public long getOrderNumber() {
		return orderNumber;
	}

	@SuppressWarnings("unused")
	private void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	@SuppressWarnings("unused")
	private void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getCustomer() {
		return customer;
	}
	
	@SuppressWarnings("unused")
	private void setCustomer(User customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}
	
	@SuppressWarnings("unused")
	private void setProduct(Product product) {
		this.product = product;
	}

	public OrderState getState() {
		return state;
	}

	void setState(OrderState state) {
		this.state = state;
	}

	public void getCancel(){
		this.state.getCancel(this);
	}
	
	public void getPrepare() {
		this.state.getPrepare(this);
	}
	
	public void getArrive() {
		this.state.getArrive(this);
	}

	public OrderState getPendingState() {
		return pendingState;
	}
	
	public OrderState getCancelState() {
		return cancelState;
	}

	public OrderState getPreparedState() {
		return preparedState;
	}

	public OrderState getArrivedState() {
		return arrivedState;
	}
	
}