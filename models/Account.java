package ru.batievsky.personalAccountViewer.models;
/*
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
*/
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Account {

    private int cons_id;
    private String fullname;
    private String serialnum;
    private String datepowerka;
    private String dateinst;
    private LinkedHashMap<String, Double> ValuePerDate;

    @NotEmpty(message = "Поле с лицевым счетом не должно быть пустым")
    @Pattern(regexp = "^[1-9]([0-9]){11}$",
            message = "Текст не соответствует синтаксису лицевого счета")
    private String accountNumber;
    /*
    @NotEmpty(message = "Telephone number should not be empty")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$",
            message = "the number does not match the syntax")
    private String telephoneNumber;
    */
    public Account() {
        ValuePerDate = new LinkedHashMap<>();
    }

    public Account(String aNum) {
        accountNumber = aNum;
        ValuePerDate = new LinkedHashMap<>();
    }

    public Account(int cons_id, String fullname, String serialnum, String datepowerka, String dateinst, LinkedHashMap<String, Double> valuePerDate, String accountNumber) {
        this.cons_id = cons_id;
        this.fullname = fullname;
        this.serialnum = serialnum;
        this.datepowerka = datepowerka;
        this.dateinst = dateinst;
        ValuePerDate = valuePerDate;
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCons_id() {
        return cons_id;
    }

    public void setCons_id(int cons_id) {
        this.cons_id = cons_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getDatepowerka() {
        return datepowerka;
    }

    public void setDatepowerka(String datepowerka) {
        this.datepowerka = datepowerka;
    }

    public String getDateinst() {
        return dateinst;
    }

    public void setDateinst(String dateinst) {
        this.dateinst = dateinst;
    }

    public LinkedHashMap<String, Double> getValuePerDate() {
        return ValuePerDate;
    }

    public void setValuePerDate(LinkedHashMap<String, Double> valuePerDate) {
        ValuePerDate = valuePerDate;
    }
}
