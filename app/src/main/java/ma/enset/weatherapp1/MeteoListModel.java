package ma.enset.weatherapp1;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView; import android.widget.TextView;
import java.util.HashMap; import java.util.List;
import java.util.Map;
public class MeteoListModel extends ArrayAdapter<MeteoItem> {
    private List<MeteoItem> listItems; private int resource;
    public static Map<String,Integer> images=new HashMap<>();
    static{
        images.put("Clear",R.drawable.clear);
        images.put("Clouds",R.drawable.clouds);
        images.put("Rain",R.drawable.rain);
        images.put("thunderstorms",R.drawable.thunderstorms);
    }
    public MeteoListModel(@NonNull Context context,int resource,
                          List<MeteoItem> data) {
        super(context, resource,data);
        Log.i("Size:",String.valueOf(data.size()));
        this.listItems=data; this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        MeteoItem meteoItem = getItem(position);

        // Lier les vues
        TextView dateText = convertView.findViewById(R.id.dateText);
        TextView tempText = convertView.findViewById(R.id.tempText);
        TextView pressureText = convertView.findViewById(R.id.pressureText);
        TextView humidityText = convertView.findViewById(R.id.humidityText);
        ImageView iconView = convertView.findViewById(R.id.weatherIcon);

        // Affecter les valeurs
        dateText.setText("📅 " + meteoItem.date);
        tempText.setText("🌡️ Min: " + meteoItem.tempMin + "°C | Max: " + meteoItem.tempMax + "°C");
        pressureText.setText("🔽 Pression: " + meteoItem.pression + " hPa");
        humidityText.setText("💧 Humidité: " + meteoItem.humidite + " %");

        // Choisir une icône locale selon le type de météo
        switch (meteoItem.image) {
            case "Rain":
                iconView.setImageResource(R.drawable.rain);
                break;
            case "Clear":
                iconView.setImageResource(R.drawable.clear);
                break;
            case "Clouds":
                iconView.setImageResource(R.drawable.clouds);
                break;
            default:
                iconView.setImageResource(R.drawable.thunderstorms);
                break;
        }

        return convertView;
    }
        }