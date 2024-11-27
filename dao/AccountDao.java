package ru.batievsky.personalAccountViewer.dao;

import org.springframework.stereotype.Component;
import ru.batievsky.personalAccountViewer.models.Account;

import java.sql.*;
import java.util.LinkedHashMap;

@Component
public class AccountDao {

    private static final String URL = "URL";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Account getAccount(Account searchedAccount){

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select cons_id,cn from t_physcons  where cn=? and active=true");

            preparedStatement.setString(1, searchedAccount.getAccountNumber());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            searchedAccount.setCons_id(resultSet.getInt("cons_id"));

            preparedStatement = connection.prepareStatement("select d.fullname,t_da.serialnum,t_da.datepowerka,t_da.dateinst\n" +
                    "from t_da left join spr_accdevice d on t_da.cnttype_id=d.id\n" +
                    "where cons_id=? and constype_id=3 and active=true");

            preparedStatement.setInt(1, searchedAccount.getCons_id());

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            searchedAccount.setDateinst(resultSet.getString("dateinst").substring(0,10));
            searchedAccount.setDatepowerka(resultSet.getString("datepowerka").substring(0,10));
            searchedAccount.setFullname(resultSet.getString("fullname"));
            searchedAccount.setSerialnum(resultSet.getString("serialnum"));

            preparedStatement = connection.prepareStatement("select  t_docs._date, val.value " +
                    "from  t_docs left join t_davalues val on  t_docs.id=val.source_id " +
                    "where  cons_id=? " +
                    "  and constype_id=3 " +
                    "  and doctype_id=4 " +
                    "  and t_docs.period_id between " +
                    "        ( select  max(period_id) " +
                    "             from  t_docs dcs " +
                    "             where  dcs.cons_id=? " +
                    "               and dcs.constype_id=3 " +
                    "               and doctype_id=4 " +
                    "               and sourcetype_id=2)-6 " +
                    "    and " +
                    "    ( select  max(period_id) " +
                    "         from  t_docs dcs " +
                    "         where  dcs.cons_id=? " +
                    "           and dcs.constype_id=3 " +
                    "           and doctype_id=4 " +
                    "           and sourcetype_id=2) " +
                    "order by _date");
            preparedStatement.setInt(1, searchedAccount.getCons_id());
            preparedStatement.setInt(2, searchedAccount.getCons_id());
            preparedStatement.setInt(3, searchedAccount.getCons_id());
            resultSet = preparedStatement.executeQuery();
            LinkedHashMap<String, Double> TemplateMap = new LinkedHashMap<>();
            while (resultSet.next()) {
                TemplateMap.put(resultSet.getString("_date").substring(0,10), resultSet.getDouble("value"));
            }
            searchedAccount.setValuePerDate(TemplateMap);
            return searchedAccount;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
