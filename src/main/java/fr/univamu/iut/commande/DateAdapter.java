package fr.univamu.iut.commande;

import jakarta.json.bind.adapter.JsonbAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter implements JsonbAdapter<Date, String> {
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String adaptToJson(Date date) {
        return date != null ? outputFormat.format(date) : null;
    }

    @Override
    public Date adaptFromJson(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }

        try {
            // Supprimer le Z à la fin si présent
            String cleanDateStr = dateStr;
            if (dateStr.endsWith("Z")) {
                cleanDateStr = dateStr.substring(0, dateStr.length() - 1);
            }

            // Parser au format yyyy-MM-dd
            return new SimpleDateFormat("yyyy-MM-dd").parse(cleanDateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Erreur lors du parsing de la date: " + dateStr, e);
        }
    }
}
