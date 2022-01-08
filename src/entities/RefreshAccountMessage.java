package entities;

import org.json.JSONObject;

public class RefreshAccountMessage extends BankMessage {

	public RefreshAccountMessage(String cardCode, String ownner, String cvvCode, String dateExpried) {
		super(cardCode, ownner, cvvCode, dateExpried);
	}

	@Override
	public String toJSONString() {
		JSONObject jsonMsg = new JSONObject();
		jsonMsg.put("cardCode", this.cardCode);
		jsonMsg.put("owner", this.ownner);
		jsonMsg.put("cvvCode", this.cvvCode);
		jsonMsg.put("dateExpried", this.dateExprired);
		return jsonMsg.toString();
	}

}