package skeleton;

public class Checkout {

	private double totalCost = 0;

	public enum Produce {
		APPLE(1.00), BANANA(1.50), GRAPES(2);

		private double value;
		private Produce(double value) {
			this.value = value;
		}
	};

	public void registerItem(Produce p) {
		totalCost += p.value;
	}
	
	public String getTotalCost(){
		return String.valueOf(totalCost);
	}

}
