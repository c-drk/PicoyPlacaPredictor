/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picoyplacapredictor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author drk
 */
class PicoyPlacaPredictor {

    String response;
   
    public String Predictor(String placa, String fecha, String hora){
        int lastdigit=0;
        if(validaPlaca(placa)){
            if(validaFecha(fecha)){
                if(validaHora(hora)){
                    lastdigit=Integer.parseInt(placa.substring(placa.length()-1,placa.length()));
                    switch(lastdigit){
                        case 1: case 2:
                            if((getDayofCalendar(fecha)==2) && picoyplacaHour(hora)){
                                response="No está en circulación";
                            }
                            else{
                                response="Está en circulación";
                            }
                            break;
                        case 3: case 4:
                            if((getDayofCalendar(fecha)==3) && picoyplacaHour(hora)){
                                response="No está en circulación";
                            }
                            else{
                                response="Está en circulación";
                            }
                            break;
                        case 5: case 6:
                            if((getDayofCalendar(fecha)==4) && picoyplacaHour(hora)){
                                response="No está en circulación";
                            }
                            else{
                                response="Está en circulación";
                            }
                            break;
                        case 7: case 8:
                            if((getDayofCalendar(fecha)==5) && picoyplacaHour(hora)){
                                response="No está en circulación";
                            }
                            else{
                                response="Está en circulación";
                            }
                            break;
                        case 9: case 0:
                            if((getDayofCalendar(fecha)==6) && picoyplacaHour(hora)){
                                response="No está en circulación";
                            }
                            else{
                                response="Está en circulación";
                            }
                            break;
                    }
                }
                else{
                    response="Hora invalida";
                }
            }
            else{
                response="Fecha invalida";
            }
        }
        else{
            response="Placa invalida";
        }      
        return response;
    }
    
    public boolean validaPlaca(String placa){
        Pattern pattern= Pattern.compile("^[a-zA-Z]{3,4}-\\d{3,4}$");
        Matcher matcher = pattern.matcher(placa);
        if (matcher.find()) {
            return true;
        }
        else{
            return false;
        }
            
    }
    public boolean validaFecha(String fecha){
        Pattern pattern= Pattern.compile("^(^(((0[1-9]|1[0-9]|2[0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\\d\\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)$");
        Matcher matcher = pattern.matcher(fecha);
        if (matcher.find()) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean validaHora(String hora){
        Pattern pattern= Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        Matcher matcher = pattern.matcher(hora);
        if (matcher.find()) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getDayofCalendar(String date){
        int dayOfWeek=0;
        try {
            Calendar c = Calendar.getInstance();
            DateFormat formatter ;
            Date mydate;
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            mydate = formatter.parse(date);
            c.setTime(mydate);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException ex) {
            Logger.getLogger(PicoyPlacaPredictor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dayOfWeek;
    }
    
    public boolean picoyplacaHour(String hour){
        int hora=Integer.parseInt(hour.split(":")[0]);
        int minutos=Integer.parseInt(hour.split(":")[1]);
        if( (hora>=7 && hora<=8) || (hora==9 && minutos<31)){
            return true;
        }
        else if((hora>=16 && hora <=18) || (hora==19 && minutos<31)){
            return true;
        }
        else{
            return false;
        }
    }
}
