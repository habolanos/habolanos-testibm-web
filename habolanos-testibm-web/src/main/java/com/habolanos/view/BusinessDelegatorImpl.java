package com.habolanos.view;

import com.habolanos.dto.AsesoresDTO;
import com.habolanos.dto.ClientesDTO;
import com.habolanos.dto.ConsumosDTO;
import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.modelo.Asesores;
import com.habolanos.modelo.Clientes;
import com.habolanos.modelo.Consumos;
import com.habolanos.modelo.TarjetasCredito;

import com.habolanos.service.AsesoresService;
import com.habolanos.service.ClientesService;
import com.habolanos.service.ConsumosService;
import com.habolanos.service.TarjetasCreditoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
@Scope("singleton")
@Component("businessDelegator")
public class BusinessDelegatorImpl implements BusinessDelegator {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorImpl.class);
    @Autowired
    private AsesoresService asesoresService;
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private ConsumosService consumosService;
    @Autowired
    private TarjetasCreditoService tarjetasCreditoService;

    public List<Asesores> getAsesores() throws Exception {
        return asesoresService.getAsesores();
    }

    public void saveAsesores(Asesores entity) throws Exception {
        asesoresService.saveAsesores(entity);
    }

    public void deleteAsesores(Asesores entity) throws Exception {
        asesoresService.deleteAsesores(entity);
    }

    public void updateAsesores(Asesores entity) throws Exception {
        asesoresService.updateAsesores(entity);
    }

    public Asesores getAsesores(Long idAsesor) throws Exception {
        Asesores asesores = null;

        try {
            asesores = asesoresService.getAsesores(idAsesor);
        } catch (Exception e) {
            throw e;
        }

        return asesores;
    }

    public List<Asesores> findByCriteriaInAsesores(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return asesoresService.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Asesores> findPageAsesores(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return asesoresService.findPageAsesores(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberAsesores() throws Exception {
        return asesoresService.findTotalNumberAsesores();
    }

    public List<AsesoresDTO> getDataAsesores() throws Exception {
        return asesoresService.getDataAsesores();
    }

    public void validateAsesores(Asesores asesores) throws Exception {
        asesoresService.validateAsesores(asesores);
    }

    public List<Clientes> getClientes() throws Exception {
        return clientesService.getClientes();
    }

    public void saveClientes(Clientes entity) throws Exception {
        clientesService.saveClientes(entity);
    }

    public void deleteClientes(Clientes entity) throws Exception {
        clientesService.deleteClientes(entity);
    }

    public void updateClientes(Clientes entity) throws Exception {
        clientesService.updateClientes(entity);
    }

    public Clientes getClientes(Long documento) throws Exception {
        Clientes clientes = null;

        try {
            clientes = clientesService.getClientes(documento);
        } catch (Exception e) {
            throw e;
        }

        return clientes;
    }

    public List<Clientes> findByCriteriaInClientes(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return clientesService.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return clientesService.findPageClientes(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberClientes() throws Exception {
        return clientesService.findTotalNumberClientes();
    }

    public List<ClientesDTO> getDataClientes() throws Exception {
        return clientesService.getDataClientes();
    }

    public void validateClientes(Clientes clientes) throws Exception {
        clientesService.validateClientes(clientes);
    }

    public List<Consumos> getConsumos() throws Exception {
        return consumosService.getConsumos();
    }

    public void saveConsumos(Consumos entity) throws Exception {
        consumosService.saveConsumos(entity);
    }

    public void deleteConsumos(Consumos entity) throws Exception {
        consumosService.deleteConsumos(entity);
    }

    public void updateConsumos(Consumos entity) throws Exception {
        consumosService.updateConsumos(entity);
    }

    public Consumos getConsumos(Long idConsumo) throws Exception {
        Consumos consumos = null;

        try {
            consumos = consumosService.getConsumos(idConsumo);
        } catch (Exception e) {
            throw e;
        }

        return consumos;
    }

    public List<Consumos> findByCriteriaInConsumos(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return consumosService.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Consumos> findPageConsumos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return consumosService.findPageConsumos(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberConsumos() throws Exception {
        return consumosService.findTotalNumberConsumos();
    }

    public List<ConsumosDTO> getDataConsumos() throws Exception {
        return consumosService.getDataConsumos();
    }

    public void validateConsumos(Consumos consumos) throws Exception {
        consumosService.validateConsumos(consumos);
    }

    public List<TarjetasCredito> getTarjetasCredito() throws Exception {
        return tarjetasCreditoService.getTarjetasCredito();
    }

    public void saveTarjetasCredito(TarjetasCredito entity)
        throws Exception {
        tarjetasCreditoService.saveTarjetasCredito(entity);
    }

    public void deleteTarjetasCredito(TarjetasCredito entity)
        throws Exception {
        tarjetasCreditoService.deleteTarjetasCredito(entity);
    }

    public void updateTarjetasCredito(TarjetasCredito entity)
        throws Exception {
        tarjetasCreditoService.updateTarjetasCredito(entity);
    }

    public TarjetasCredito getTarjetasCredito(Long idTarjeta)
        throws Exception {
        TarjetasCredito tarjetasCredito = null;

        try {
            tarjetasCredito = tarjetasCreditoService.getTarjetasCredito(idTarjeta);
        } catch (Exception e) {
            throw e;
        }

        return tarjetasCredito;
    }

    public List<TarjetasCredito> findByCriteriaInTarjetasCredito(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tarjetasCreditoService.findByCriteria(variables,
            variablesBetween, variablesBetweenDates);
    }

    public List<TarjetasCredito> findPageTarjetasCredito(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return tarjetasCreditoService.findPageTarjetasCredito(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTarjetasCredito() throws Exception {
        return tarjetasCreditoService.findTotalNumberTarjetasCredito();
    }

    public List<TarjetasCreditoDTO> getDataTarjetasCredito()
        throws Exception {
        return tarjetasCreditoService.getDataTarjetasCredito();
    }

    public void validateTarjetasCredito(TarjetasCredito tarjetasCredito)
        throws Exception {
        tarjetasCreditoService.validateTarjetasCredito(tarjetasCredito);
    }
}
