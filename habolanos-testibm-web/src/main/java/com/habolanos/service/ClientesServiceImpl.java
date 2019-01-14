package com.habolanos.service;

import com.habolanos.dto.ClientesDTO;

import com.habolanos.exception.*;

import com.habolanos.mapper.ClientesMapper;

import com.habolanos.modelo.*;

import com.habolanos.repository.*;

import com.habolanos.utility.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
@Scope("singleton")
@Service
public class ClientesServiceImpl implements ClientesService {
    private static final Logger log = LoggerFactory.getLogger(ClientesServiceImpl.class);

    /**
     * Repository injected by Spring that manages Clientes entities
     *
     */
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private ClientesMapper clientesMapper;
    @Autowired
    private Validator validator;

    /**
    * Repository injected by Spring that manages TarjetasCredito entities
    *
    */
    @Autowired
    private TarjetasCreditoRepository tarjetasCreditoRepository;

    public void validateClientes(Clientes clientes) throws Exception {
        try {
            Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
                    strMessage.append(" - ");
                    strMessage.append(constraintViolation.getMessage());
                    strMessage.append(". \n");
                }

                throw new Exception(strMessage.toString());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Clientes> getClientes() throws Exception {
        log.debug("finding all Clientes instances");

        List<Clientes> list = new ArrayList<Clientes>();

        try {
            list = clientesRepository.findAll();
        } catch (Exception e) {
            log.error("finding all Clientes failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Clientes");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveClientes(Clientes entity) throws Exception {
        log.debug("saving Clientes instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Clientes");
            }

            validateClientes(entity);

            if (getClientes(entity.getDocumento()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            clientesRepository.save(entity);
            log.debug("save Clientes successful");
        } catch (Exception e) {
            log.error("save Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteClientes(Clientes entity) throws Exception {
        log.debug("deleting Clientes instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Clientes");
        }

        if (entity.getDocumento() == null) {
            throw new ZMessManager().new EmptyFieldException("documento");
        }

        List<TarjetasCredito> tarjetasCreditos = null;

        try {
            tarjetasCreditos = tarjetasCreditoRepository.findByProperty("clientes.documento",
                    entity.getDocumento());

            if (Utilities.validationsList(tarjetasCreditos) == true) {
                throw new ZMessManager().new DeletingException(
                    "tarjetasCreditos");
            }

            clientesRepository.deleteById(entity.getDocumento());
            log.debug("delete Clientes successful");
        } catch (Exception e) {
            log.error("delete Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateClientes(Clientes entity) throws Exception {
        log.debug("updating Clientes instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Clientes");
            }

            validateClientes(entity);

            clientesRepository.update(entity);

            log.debug("update Clientes successful");
        } catch (Exception e) {
            log.error("update Clientes failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ClientesDTO> getDataClientes() throws Exception {
        try {
            List<Clientes> clientes = clientesRepository.findAll();

            List<ClientesDTO> clientesDTO = new ArrayList<ClientesDTO>();

            for (Clientes clientesTmp : clientes) {
                ClientesDTO clientesDTO2 = clientesMapper.clientesToClientesDTO(clientesTmp);
                clientesDTO.add(clientesDTO2);
            }

            return clientesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Clientes getClientes(Long documento) throws Exception {
        log.debug("getting Clientes instance");

        Clientes entity = null;

        try {
            if (clientesRepository.findById(documento).isPresent()) {
                entity = clientesRepository.findById(documento).get();
            }
        } catch (Exception e) {
            log.error("get Clientes failed", e);
            throw new ZMessManager().new FindingException("Clientes");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Clientes> entity = null;

        try {
            entity = clientesRepository.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Clientes Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberClientes() throws Exception {
        Long entity = null;

        try {
            entity = clientesRepository.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Clientes Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Clientes> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Clientes> list = new ArrayList<Clientes>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = clientesRepository.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
