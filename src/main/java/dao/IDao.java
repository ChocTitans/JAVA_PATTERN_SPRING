package dao;

public interface IDao<T,ID> {
    T trouveParId(ID id);
}
