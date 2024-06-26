package com.example.servidorrestinesmr.data.dao.impl;

import com.example.servidorrestinesmr.data.dao.DaoUbi;
import com.example.servidorrestinesmr.data.dao.connection.JPAUtil;
import com.example.servidorrestinesmr.data.model.entities.UbiEntity;
import com.example.servidorrestinesmr.domain.model.NewUbiDTO;
import com.example.servidorrestinesmr.domain.model.UbiDTO;
import com.example.servidorrestinesmr.domain.model.error.ErrorSec;
import com.example.servidorrestinesmr.domain.model.error.exceptions.DatabaseException;
import com.example.servidorrestinesmr.utils.Constantes;
import io.vavr.control.Either;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoUbiImpl implements DaoUbi {

    private EntityManager em;
    private final JPAUtil jpaUtil;

    public DaoUbiImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<ErrorSec, List<UbiDTO>> getAll() {
        Either<ErrorSec, List<UbiDTO>> res;
        List<UbiDTO> ubiList = new ArrayList<>();
        List<UbiEntity> ubiEntityList;
        em = jpaUtil.getEntityManager();
        try {
            ubiEntityList = em
                    .createNamedQuery(Constantes.GET_ALL_UBIS, UbiEntity.class)
                    .getResultList();
            if (!ubiEntityList.isEmpty()) {
                for (UbiEntity ubiEntity : ubiEntityList) {
                    UbiDTO nuevoUbiDTO = new UbiDTO(ubiEntity.getId(), ubiEntity.getLat(), ubiEntity.getLon(), ubiEntity.getNombre());
                    ubiList.add(nuevoUbiDTO);
                }
                res = Either.right(ubiList);
            } else {
                throw new DatabaseException(Constantes.NO_SE_HAN_ENCONTRADO_UBICACIONES);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, UbiDTO> getByIdUbi(int id) {
        Either<ErrorSec, UbiDTO> res;
        UbiDTO ubiDTO;
        UbiEntity ubiEntity;
        em = jpaUtil.getEntityManager();
        try {
            ubiEntity = em.find(UbiEntity.class, id);
            if (ubiEntity != null) {
                ubiDTO = new UbiDTO(ubiEntity.getId(), ubiEntity.getLat(), ubiEntity.getLon(), ubiEntity.getNombre());
                res = Either.right(ubiDTO);
            } else {
                throw new DatabaseException(Constantes.NO_SE_HAN_ENCONTRADO_UNA_UBICACION_CON_EL_ID_PROPORCIONADO);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, List<UbiDTO>> getAllByUserId(int idUser) {
        Either<ErrorSec, List<UbiDTO>> res;
        List<UbiDTO> ubiList = new ArrayList<>();
        List<UbiEntity> ubiEntityList;
        em = jpaUtil.getEntityManager();
        try {
            ubiEntityList = em
                    .createNamedQuery(Constantes.GET_ALL_UBIS_BY_IDUSER, UbiEntity.class)
                    .setParameter(Constantes.ID_USER, idUser)
                    .getResultList();
            if (!ubiEntityList.isEmpty()) {
                for (UbiEntity ubiEntity : ubiEntityList) {
                    UbiDTO nuevoUbiDTO = new UbiDTO(ubiEntity.getId(), ubiEntity.getLat(), ubiEntity.getLon(), ubiEntity.getNombre());
                    ubiList.add(nuevoUbiDTO);
                }
                res = Either.right(ubiList);
            } else {
                throw new DatabaseException(Constantes.NO_SE_HAN_ENCONTRADO_UBICACIONES);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ErrorSec, Integer> deleteUbi(int id) {
        Either<ErrorSec, Integer> res;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        UbiEntity ubiEntity;
        try {
            ubiEntity = em.find(UbiEntity.class, id);
            em.remove(em.merge(ubiEntity));
            tx.commit();
            res = Either.right(1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (tx.isActive()) tx.rollback();
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }finally {
            if (em != null) em.close();
        }
        return res;
    }

    @Override
    public Either<ErrorSec, UbiDTO> addUbi(NewUbiDTO nuevaUbi) {
        Either<ErrorSec, UbiDTO> res;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            if (nuevaUbi != null){
                UbiEntity nuevaUbiEntity = new UbiEntity(0, nuevaUbi.getLon(), nuevaUbi.getLat(), nuevaUbi.getIdUser(), nuevaUbi.getNombre());
                em.persist(nuevaUbiEntity);
                tx.commit();
                UbiDTO ubiDTO = new UbiDTO(nuevaUbiEntity.getId(), nuevaUbi.getLat(), nuevaUbi.getLon(), nuevaUbi.getNombre());
                res = Either.right(ubiDTO);
            }else {
                throw new DatabaseException(Constantes.LA_UBICACION_ES_NULA_HUBO_UN_ERROR);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            if (tx.isActive()) tx.rollback();
            res = Either.left(new ErrorSec(0, e.getMessage(), LocalDateTime.now()));
        }finally {
            if (em != null) em.close();
        }
        return res;
    }
}
