package com.habolanos.view;

import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.exception.*;

import com.habolanos.modelo.*;

import com.habolanos.utility.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputmask.InputMask;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author habolanos
 * @email haroldadrian@gmail.com
 *
 */
@ManagedBean
@ViewScoped
public class TarjetasCreditoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TarjetasCreditoView.class);
    private InputMask txtCcv;
    private InputText txtEstado;
    private InputText txtFranquicia;
    private InputText txtMontoAvances;
    private InputText txtMontoCredito;
    private InputMask txtNumero;
    
    private InputText txtIdAsesor_Asesores;
    private InputText txtDocumento_Clientes;
    private InputText txtIdTarjeta;
    private Calendar txtFechaDesde;
    private Calendar txtFechaHasta;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TarjetasCreditoDTO> data;
    private TarjetasCreditoDTO selectedTarjetasCredito;
    private TarjetasCredito entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    public TarjetasCreditoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedTarjetasCredito = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTarjetasCredito = null;

        if (txtCcv != null) {
            txtCcv.setValue(null);
            txtCcv.setDisabled(true);
        }

        if (txtEstado != null) {
            txtEstado.setValue(null);
            txtEstado.setDisabled(true);
        }

        if (txtFranquicia != null) {
            txtFranquicia.setValue(null);
            txtFranquicia.setDisabled(true);
        }

        if (txtMontoAvances != null) {
            txtMontoAvances.setValue(null);
            txtMontoAvances.setDisabled(true);
        }

        if (txtMontoCredito != null) {
            txtMontoCredito.setValue(null);
            txtMontoCredito.setDisabled(true);
        }

        if (txtNumero != null) {
            txtNumero.setValue(null);
            txtNumero.setDisabled(true);
        }

     

        if (txtIdAsesor_Asesores != null) {
            txtIdAsesor_Asesores.setValue(null);
            txtIdAsesor_Asesores.setDisabled(true);
        }

        if (txtDocumento_Clientes != null) {
            txtDocumento_Clientes.setValue(null);
            txtDocumento_Clientes.setDisabled(true);
        }

        if (txtFechaDesde != null) {
            txtFechaDesde.setValue(null);
            txtFechaDesde.setDisabled(true);
        }

        if (txtFechaHasta != null) {
            txtFechaHasta.setValue(null);
            txtFechaHasta.setDisabled(true);
        }

        if (txtIdTarjeta != null) {
            txtIdTarjeta.setValue(null);
            txtIdTarjeta.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaDesde() {
        Date inputDate = (Date) txtFechaDesde.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaHasta() {
        Date inputDate = (Date) txtFechaHasta.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idTarjeta = FacesUtils.checkLong(txtIdTarjeta);
            entity = (idTarjeta != null)
                ? businessDelegatorView.getTarjetasCredito(idTarjeta) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCcv.setDisabled(false);
            txtEstado.setDisabled(false);
            txtFranquicia.setDisabled(false);
            txtMontoAvances.setDisabled(false);
            txtMontoCredito.setDisabled(false);
            txtNumero.setDisabled(false);
            txtIdAsesor_Asesores.setDisabled(false);
            txtDocumento_Clientes.setDisabled(false);
            txtFechaDesde.setDisabled(false);
            txtFechaHasta.setDisabled(false);
            txtIdTarjeta.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCcv.setValue(entity.getCcv());
            txtCcv.setDisabled(false);
            txtEstado.setValue(entity.getEstado());
            txtEstado.setDisabled(false);
            txtFechaDesde.setValue(entity.getFechaDesde());
            txtFechaDesde.setDisabled(false);
            txtFechaHasta.setValue(entity.getFechaHasta());
            txtFechaHasta.setDisabled(false);
            txtFranquicia.setValue(entity.getFranquicia());
            txtFranquicia.setDisabled(false);
            txtMontoAvances.setValue(entity.getMontoAvances());
            txtMontoAvances.setDisabled(false);
            txtMontoCredito.setValue(entity.getMontoCredito());
            txtMontoCredito.setDisabled(false);
            txtNumero.setValue(entity.getNumero());
            txtNumero.setDisabled(false);
            
            txtIdAsesor_Asesores.setValue(entity.getAsesores().getIdAsesor());
            txtIdAsesor_Asesores.setDisabled(false);
            txtDocumento_Clientes.setValue(entity.getClientes().getDocumento());
            txtDocumento_Clientes.setDisabled(false);
            txtIdTarjeta.setValue(entity.getIdTarjeta());
            txtIdTarjeta.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTarjetasCredito = (TarjetasCreditoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTarjetasCredito"));
        txtCcv.setValue(selectedTarjetasCredito.getCcv());
        txtCcv.setDisabled(false);
        txtEstado.setValue(selectedTarjetasCredito.getEstado());
        txtEstado.setDisabled(false);
        txtFechaDesde.setValue(selectedTarjetasCredito.getFechaDesde());
        txtFechaDesde.setDisabled(false);
        txtFechaHasta.setValue(selectedTarjetasCredito.getFechaHasta());
        txtFechaHasta.setDisabled(false);
        txtFranquicia.setValue(selectedTarjetasCredito.getFranquicia());
        txtFranquicia.setDisabled(false);
        txtMontoAvances.setValue(selectedTarjetasCredito.getMontoAvances());
        txtMontoAvances.setDisabled(false);
        txtMontoCredito.setValue(selectedTarjetasCredito.getMontoCredito());
        txtMontoCredito.setDisabled(false);
        txtNumero.setValue(selectedTarjetasCredito.getNumero());
        txtNumero.setDisabled(false);
        
        txtIdAsesor_Asesores.setValue(selectedTarjetasCredito.getIdAsesor_Asesores());
        txtIdAsesor_Asesores.setDisabled(false);
        txtDocumento_Clientes.setValue(selectedTarjetasCredito.getDocumento_Clientes());
        txtDocumento_Clientes.setDisabled(false);
        txtIdTarjeta.setValue(selectedTarjetasCredito.getIdTarjeta());
        txtIdTarjeta.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTarjetasCredito == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new TarjetasCredito();

            Long idTarjeta = FacesUtils.checkLong(txtIdTarjeta);

            entity.setCcv(txtCcv.getValue().toString());
            entity.setEstado(FacesUtils.checkLong(txtEstado));
            entity.setFechaDesde(FacesUtils.checkDate(txtFechaDesde));
            entity.setFechaHasta(FacesUtils.checkDate(txtFechaHasta));
            entity.setFranquicia(FacesUtils.checkString(txtFranquicia));
            entity.setIdTarjeta(idTarjeta);
            entity.setMontoAvances(FacesUtils.checkDouble(txtMontoAvances));
            entity.setMontoCredito(FacesUtils.checkDouble(txtMontoCredito));
            entity.setNumero(new Long(txtNumero.getValue().toString().replaceAll(" ", "")));
            
            entity.setAsesores((FacesUtils.checkLong(txtIdAsesor_Asesores) != null)
                ? businessDelegatorView.getAsesores(FacesUtils.checkLong(
                        txtIdAsesor_Asesores)) : null);
            entity.setClientes((FacesUtils.checkLong(txtDocumento_Clientes) != null)
                ? businessDelegatorView.getClientes(FacesUtils.checkLong(
                        txtDocumento_Clientes)) : null);
            businessDelegatorView.saveTarjetasCredito(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long idTarjeta = new Long(selectedTarjetasCredito.getIdTarjeta());
                entity = businessDelegatorView.getTarjetasCredito(idTarjeta);
            }

            entity.setCcv(txtCcv.getValue().toString());
            entity.setEstado(FacesUtils.checkLong(txtEstado));
            entity.setFechaDesde(FacesUtils.checkDate(txtFechaDesde));
            entity.setFechaHasta(FacesUtils.checkDate(txtFechaHasta));
            entity.setFranquicia(FacesUtils.checkString(txtFranquicia));
            entity.setMontoAvances(FacesUtils.checkDouble(txtMontoAvances));
            entity.setMontoCredito(FacesUtils.checkDouble(txtMontoCredito));
            entity.setNumero(new Long(txtNumero.getValue().toString()));
            
            entity.setAsesores((FacesUtils.checkLong(txtIdAsesor_Asesores) != null)
                ? businessDelegatorView.getAsesores(FacesUtils.checkLong(
                        txtIdAsesor_Asesores)) : null);
            entity.setClientes((FacesUtils.checkLong(txtDocumento_Clientes) != null)
                ? businessDelegatorView.getClientes(FacesUtils.checkLong(
                        txtDocumento_Clientes)) : null);
            businessDelegatorView.updateTarjetasCredito(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTarjetasCredito = (TarjetasCreditoDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedTarjetasCredito"));

            Long idTarjeta = new Long(selectedTarjetasCredito.getIdTarjeta());
            entity = businessDelegatorView.getTarjetasCredito(idTarjeta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idTarjeta = FacesUtils.checkLong(txtIdTarjeta);
            entity = businessDelegatorView.getTarjetasCredito(idTarjeta);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTarjetasCredito(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(String ccv, Long estado, Date fechaDesde,
        Date fechaHasta, String franquicia, Long idTarjeta,
        Double montoAvances, Double montoCredito, Long numero, String numero01,
        String numero02, String numero03, String numero04,
        Long idAsesor_Asesores, Long documento_Clientes)
        throws Exception {
        try {
            entity.setCcv(FacesUtils.checkString(ccv));
            entity.setEstado(FacesUtils.checkLong(estado));
            entity.setFechaDesde(FacesUtils.checkDate(fechaDesde));
            entity.setFechaHasta(FacesUtils.checkDate(fechaHasta));
            entity.setFranquicia(FacesUtils.checkString(franquicia));
            entity.setMontoAvances(FacesUtils.checkDouble(montoAvances));
            entity.setMontoCredito(FacesUtils.checkDouble(montoCredito));
            entity.setNumero(FacesUtils.checkLong(numero));
            
            businessDelegatorView.updateTarjetasCredito(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TarjetasCreditoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputMask getTxtCcv() {
        return txtCcv;
    }

    public void setTxtCcv(InputMask txtCcv) {
        this.txtCcv = txtCcv;
    }

    public InputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(InputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public InputText getTxtFranquicia() {
        return txtFranquicia;
    }

    public void setTxtFranquicia(InputText txtFranquicia) {
        this.txtFranquicia = txtFranquicia;
    }

    public InputText getTxtMontoAvances() {
        return txtMontoAvances;
    }

    public void setTxtMontoAvances(InputText txtMontoAvances) {
        this.txtMontoAvances = txtMontoAvances;
    }

    public InputText getTxtMontoCredito() {
        return txtMontoCredito;
    }

    public void setTxtMontoCredito(InputText txtMontoCredito) {
        this.txtMontoCredito = txtMontoCredito;
    }

    public InputMask getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(InputMask txtNumero) {
        this.txtNumero = txtNumero;
    }

    

    public InputText getTxtIdAsesor_Asesores() {
        return txtIdAsesor_Asesores;
    }

    public void setTxtIdAsesor_Asesores(InputText txtIdAsesor_Asesores) {
        this.txtIdAsesor_Asesores = txtIdAsesor_Asesores;
    }

    public InputText getTxtDocumento_Clientes() {
        return txtDocumento_Clientes;
    }

    public void setTxtDocumento_Clientes(InputText txtDocumento_Clientes) {
        this.txtDocumento_Clientes = txtDocumento_Clientes;
    }

    public Calendar getTxtFechaDesde() {
        return txtFechaDesde;
    }

    public void setTxtFechaDesde(Calendar txtFechaDesde) {
        this.txtFechaDesde = txtFechaDesde;
    }

    public Calendar getTxtFechaHasta() {
        return txtFechaHasta;
    }

    public void setTxtFechaHasta(Calendar txtFechaHasta) {
        this.txtFechaHasta = txtFechaHasta;
    }

    public InputText getTxtIdTarjeta() {
        return txtIdTarjeta;
    }

    public void setTxtIdTarjeta(InputText txtIdTarjeta) {
        this.txtIdTarjeta = txtIdTarjeta;
    }

    public List<TarjetasCreditoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTarjetasCredito();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TarjetasCreditoDTO> tarjetasCreditoDTO) {
        this.data = tarjetasCreditoDTO;
    }

    public TarjetasCreditoDTO getSelectedTarjetasCredito() {
        return selectedTarjetasCredito;
    }

    public void setSelectedTarjetasCredito(TarjetasCreditoDTO tarjetasCredito) {
        this.selectedTarjetasCredito = tarjetasCredito;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public BusinessDelegator getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        BusinessDelegator businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
