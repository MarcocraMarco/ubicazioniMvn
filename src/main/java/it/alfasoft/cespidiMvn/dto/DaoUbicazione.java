package it.alfasoft.cespidiMvn.dto;




import it.alfasoft.cespidiMvn.dao.DaoException;
import it.alfasoft.cespidiMvn.dao.DaoSuper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUbicazione extends DaoSuper<Ubicazione,Long> {
    String nomeDb=null;
    public DaoUbicazione(String db){
        nomeDb=db;
    }
    @Override
    public List<Ubicazione> list() throws DaoException{
        List<Ubicazione> listsSt=new ArrayList<>();
        try{
            String queryList="select * from"+nomeDb+".ubicazione ";
            PreparedStatement ps1=getPrepStatement(getConnection(),queryList);
            ResultSet rs=ps1.executeQuery();
            while(rs.next()){
                listsSt.add(new Ubicazione(rs.getString("nome"),
                        rs.getLong("idUbicazione")));
            }
            return listsSt;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("errore metodo list");
        }
    }
    @Override
    public Long add(Ubicazione elemento)throws DaoException{

        try{
            String queryAdd="insert into "+nomeDb+".ubicazione (nome) values(?)";
            PreparedStatement ps2=getPrepStatement(getConnection(),queryAdd);
            ps2.setString(1, elemento.getNomeUbicazione());
            return (Long) (long)ps2.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("errore nel metodo add");
        }
    }
    @Override
   public Long delete(Long id) throws DaoException{
        String queryById="delete from "+nomeDb+".stato where idUbicazione=?";
        try{
            PreparedStatement ps3=getPrepStatement(getConnection(),queryById);
            ps3.setLong(1,id);
            return (long)ps3.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("errore getBdyId");
        }

    }
    @Override
    public Ubicazione getById(Long indice)throws DaoException {
        Ubicazione st=null;
        String queryGetById="Select * from "+nomeDb+".stato where idUbicazione=?";
        try {
            PreparedStatement ps4 = getPrepStatement(getConnection(), queryGetById);
            ps4.setLong(1,indice);
            ResultSet rs= ps4.executeQuery();
            if(rs.next()){
                st=new Ubicazione(rs.getString("nome"),rs.getLong("idUbicazione"));
            }
            return st;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("problema metodo getById");
        }
    }
    @Override
    public Long update(Long id,Ubicazione ub) throws DaoException {
        String queryUpdate="update "+nomeDb+".ubicazione set ";
        try{
            PreparedStatement ps=getPrepStatement(getConnection(),queryUpdate);
            ps.setString(1,ub.getNomeUbicazione());
            //MA basta ti uon
            return (long)ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("errore metodo update");
        }
    }
    @Override
    public List<Ubicazione> find(String searchText) throws DaoException {
        List<Ubicazione> listaUb=new ArrayList<>();
        try{
            String querySearch="select * from "+nomeDb+".cespiti where nome like ?";
            PreparedStatement ps4=getPrepStatement(getConnection(),querySearch);
            ps4.setString(1,"%"+searchText+"%");
            ResultSet rs=ps4.executeQuery();
            while(rs.next()){
                listaUb.add(new Ubicazione(rs.getString("nome"),rs.getLong("idUbicazione")));
            }
            return listaUb;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("Errore nel metodo findTxt");
        }
    }

    public List<Ubicazione> find(Ubicazione searchtext) throws DaoException {
        List<Ubicazione> listub= new ArrayList<>();

        String querySearc="select * from "+nomeDb+".cespiti where nome like ? ";
        try {
            PreparedStatement pr5 = getPrepStatement(getConnection(), querySearc);
            pr5.setString(1,"%"+searchtext+"%");
            ResultSet rs=pr5.executeQuery();
            while(rs.next()){
                listub.add(new Ubicazione(rs.getString("nome"),rs.getLong("IdUbicazione")));
            }
            return listub;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DaoException("Errore nel metodo findOggetto");
        }
    }

}
