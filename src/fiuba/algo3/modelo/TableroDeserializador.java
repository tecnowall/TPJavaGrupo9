package fiuba.algo3.modelo;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TableroDeserializador implements JsonDeserializer<Tablero> {

	@Override
	public Tablero deserialize(JsonElement json, Type tipo, JsonDeserializationContext context) throws JsonParseException {
		int ancho = json.getAsJsonObject().get("ancho").getAsInt();
		int alto = json.getAsJsonObject().get("alto").getAsInt();
		
		Tablero tablero = new Tablero( ancho, alto );
		
		return tablero;
	}

}
