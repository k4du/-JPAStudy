package br.com.jpaestudo.dao;

import br.com.jpaestudo.entity.Document;


public class DocumentDAO extends GenericDAO<Document> {

    public DocumentDAO() {
        super(Document.class);
    }
}
