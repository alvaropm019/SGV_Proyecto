/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author apermac
 */

package GUI;

import java.util.List;

public class ConflictManager {
    // Radio de la Tierra en Millas Náuticas
    private static final double EARTH_RADIUS_NM = 3440.065;

    public static void updateConflicts(List<TrafficGraphic> trafficList, double maxDistNM, boolean activo) {  
        

        // Si el usuario pulsó cancelar, reseteamos todos los conflictos a false y salimos
        if (!activo) {
            for (TrafficGraphic t : trafficList) t.setInConflict(false);
            return;
        }
        

        // Comparar todos con todos
        for (int i = 0; i < trafficList.size(); i++) {
            for (int j = i + 1; j < trafficList.size(); j++) {
                TrafficGraphic t1 = trafficList.get(i);
                TrafficGraphic t2 = trafficList.get(j);

                if (isConflict(t1, t2, maxDistNM)) {
                    t1.setInConflict(true);
                    t2.setInConflict(true);
                }
            }
        }
    }

    private static boolean isConflict(TrafficGraphic t1, TrafficGraphic t2, double maxDistNM) {
        // Accedemos a la altitud a través de la clase Traffic
        // Usamos getAlt_ext() que es el método real para la altitud
        double alt1 = t1.getTraffic().getAltitude();
        double alt2 = t2.getTraffic().getAltitude();

        double altDiff = Math.abs(alt1 - alt2);
        
        // Para la latitud y longitud, también accedemos vía Traffic
        // El método getPosition() devuelve un objeto con Lat y Lon
        double lat1 = t1.getTraffic().getPosition().getLatitude();
        double lon1 = t1.getTraffic().getPosition().getLongitude(); 
        double lat2 = t2.getTraffic().getPosition().getLatitude(); 
        double lon2 = t2.getTraffic().getPosition().getLongitude(); 
              
        double dist = calcularDistancia(lat1, lon1, lat2, lon2);

        // Alerta si: Distancia < 5NM Y Altitud < 1000ft
        return (dist < maxDistNM);// && altDiff < 1000);
    }

    private static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_NM * c;
    }
}
//holagi