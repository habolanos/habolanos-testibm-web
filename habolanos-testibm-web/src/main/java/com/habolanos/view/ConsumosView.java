package com.habolanos.view;

import com.habolanos.dto.ConsumosDTO;

import com.habolanos.exception.*;

import com.habolanos.modelo.*;

import com.habolanos.utility.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
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
public class ConsumosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumosView.class);
    private InputText txtDescripcion;
    private InputText txtMonto;
    private InputText txtIdTarjeta_TarjetasCredito;
    private InputText txtIdConsumo;
    private Calendar txtFecha;
    private Calendar txtFechaHoraRegistro;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ConsumosDTO> data;
    private ConsumosDTO selectedConsumos;
    private Consumos entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    public ConsumosView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedConsumos = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedConsumos = null;

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtMonto != null) {
            txtMonto.setValue(null);
            txtMonto.setDisabled(true);
        }

        if (txtIdTarjeta_TarjetasCredito != null) {
            txtIdTarjeta_TarjetasCredito.setValue(null);
            txtIdTarjeta_TarjetasCredito.setDisabled(true);
        }

        if (txtFecha != null) {
            txtFecha.setValue(null);
            txtFecha.setDisabled(true);
        }

        if (txtFechaHoraRegistro != null) {
            txtFechaHoraRegistro.setValue(null);
            txtFechaHoraRegistro.setDisabled(true);
        }

        if (txtIdConsumo != null) {
            txtIdConsumo.setValue(null);
            txtIdConsumo.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFecha() {
        Date inputDate = (Date) txtFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaHoraRegistro() {
        Date inputDate = (Date) txtFechaHoraRegistro.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idConsumo = FacesUtils.checkLong(txtIdConsumo);
            entity = (idConsumo != null)
                ? businessDelegatorView.getConsumos(idConsumo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcion.setDisabled(false);
            txtMonto.setDisabled(false);
            txtIdTarjeta_TarjetasCredito.setDisabled(false);
            txtFecha.setDisabled(false);
            txtFechaHoraRegistro.setDisabled(false);
            txtIdConsumo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtFecha.setValue(entity.getFecha());
            txtFecha.setDisabled(false);
            txtFechaHoraRegistro.setValue(entity.getFechaHoraRegistro());
            txtFechaHoraRegistro.setDisabled(false);
            txtMonto.setValue(entity.getMonto());
            txtMonto.setDisabled(false);
            txtIdTarjeta_TarjetasCredito.setValue(entity.getTarjetasCredito()
                                                        .getIdTarjeta());
            txtIdTarjeta_TarjetasCredito.setDisabled(false);
            txtIdConsumo.setValue(entity.getIdConsumo());
            txtIdConsumo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedConsumos = (ConsumosDTO) (evt.getComponent().getAttributes()
                                             .get("selectedConsumos"));
        txtDescripcion.setValue(selectedConsumos.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtFecha.setValue(selectedConsumos.getFecha());
        txtFecha.setDisabled(false);
        txtFechaHoraRegistro.setValue(selectedConsumos.getFechaHoraRegistro());
        txtFechaHoraRegistro.setDisabled(false);
        txtMonto.setValue(selectedConsumos.getMonto());
        txtMonto.setDisabled(false);
        txtIdTarjeta_TarjetasCredito.setValue(selectedConsumos.getIdTarjeta_TarjetasCredito());
        txtIdTarjeta_TarjetasCredito.setDisabled(false);
        txtIdConsumo.setValue(selectedConsumos.getIdConsumo());
        txtIdConsumo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedConsumos == null) && (entity == null)) {
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
            entity = new Consumos();

            Long idConsumo = FacesUtils.checkLong(txtIdConsumo);

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setFechaHoraRegistro(FacesUtils.checkDate(
                    txtFechaHoraRegistro));
            entity.setIdConsumo(idConsumo);
            entity.setMonto(FacesUtils.checkDouble(txtMonto));
            entity.setTarjetasCredito((FacesUtils.checkLong(
                    txtIdTarjeta_TarjetasCredito) != null)
                ? businessDelegatorView.getTarjetasCredito(FacesUtils.checkLong(
                        txtIdTarjeta_TarjetasCredito)) : null);
            businessDelegatorView.saveConsumos(entity);
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
                Long idConsumo = new Long(selectedConsumos.getIdConsumo());
                entity = businessDelegatorView.getConsumos(idConsumo);
            }

            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setFecha(FacesUtils.checkDate(txtFecha));
            entity.setFechaHoraRegistro(FacesUtils.checkDate(
                    txtFechaHoraRegistro));
            entity.setMonto(FacesUtils.checkDouble(txtMonto));
            entity.setTarjetasCredito((FacesUtils.checkLong(
                    txtIdTarjeta_TarjetasCredito) != null)
                ? businessDelegatorView.getTarjetasCredito(FacesUtils.checkLong(
                        txtIdTarjeta_TarjetasCredito)) : null);
            businessDelegatorView.updateConsumos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedConsumos = (ConsumosDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedConsumos"));

            Long idConsumo = new Long(selectedConsumos.getIdConsumo());
            entity = businessDelegatorView.getConsumos(idConsumo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idConsumo = FacesUtils.checkLong(txtIdConsumo);
            entity = businessDelegatorView.getConsumos(idConsumo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteConsumos(entity);
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

    public String action_modifyWitDTO(String descripcion, Date fecha,
        Date fechaHoraRegistro, Long idConsumo, Double monto,
        Long idTarjeta_TarjetasCredito) throws Exception {
        try {
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setFecha(FacesUtils.checkDate(fecha));
            entity.setFechaHoraRegistro(FacesUtils.checkDate(fechaHoraRegistro));
            entity.setMonto(FacesUtils.checkDouble(monto));
            businessDelegatorView.updateConsumos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ConsumosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtMonto() {
        return txtMonto;
    }

    public void setTxtMonto(InputText txtMonto) {
        this.txtMonto = txtMonto;
    }

    public InputText getTxtIdTarjeta_TarjetasCredito() {
        return txtIdTarjeta_TarjetasCredito;
    }

    public void setTxtIdTarjeta_TarjetasCredito(
        InputText txtIdTarjeta_TarjetasCredito) {
        this.txtIdTarjeta_TarjetasCredito = txtIdTarjeta_TarjetasCredito;
    }

    public Calendar getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(Calendar txtFecha) {
        this.txtFecha = txtFecha;
    }

    public Calendar getTxtFechaHoraRegistro() {
        return txtFechaHoraRegistro;
    }

    public void setTxtFechaHoraRegistro(Calendar txtFechaHoraRegistro) {
        this.txtFechaHoraRegistro = txtFechaHoraRegistro;
    }

    public InputText getTxtIdConsumo() {
        return txtIdConsumo;
    }

    public void setTxtIdConsumo(InputText txtIdConsumo) {
        this.txtIdConsumo = txtIdConsumo;
    }

    public List<ConsumosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataConsumos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ConsumosDTO> consumosDTO) {
        this.data = consumosDTO;
    }

    public ConsumosDTO getSelectedConsumos() {
        return selectedConsumos;
    }

    public void setSelectedConsumos(ConsumosDTO consumos) {
        this.selectedConsumos = consumos;
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
