package com.habolanos.view;

import com.habolanos.dto.ClientesDTO;

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
public class ClientesView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ClientesView.class);
    private InputText txtApellidos;
    private InputText txtCiudad;
    private InputText txtDireccion;
    private InputText txtNombres;
    private InputText txtNumeroTarjetasAsociadas;
    private InputText txtTelefono;
    private InputText txtTipoDocumento;
    private InputText txtDocumento;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ClientesDTO> data;
    private ClientesDTO selectedClientes;
    private Clientes entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    public ClientesView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedClientes = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedClientes = null;

        if (txtApellidos != null) {
            txtApellidos.setValue(null);
            txtApellidos.setDisabled(true);
        }

        if (txtCiudad != null) {
            txtCiudad.setValue(null);
            txtCiudad.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtNombres != null) {
            txtNombres.setValue(null);
            txtNombres.setDisabled(true);
        }

        if (txtNumeroTarjetasAsociadas != null) {
            txtNumeroTarjetasAsociadas.setValue(null);
            txtNumeroTarjetasAsociadas.setDisabled(true);
        }

        if (txtTelefono != null) {
            txtTelefono.setValue(null);
            txtTelefono.setDisabled(true);
        }

        if (txtTipoDocumento != null) {
            txtTipoDocumento.setValue(null);
            txtTipoDocumento.setDisabled(true);
        }

        if (txtDocumento != null) {
            txtDocumento.setValue(null);
            txtDocumento.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long documento = FacesUtils.checkLong(txtDocumento);
            entity = (documento != null)
                ? businessDelegatorView.getClientes(documento) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellidos.setDisabled(false);
            txtCiudad.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtNombres.setDisabled(false);
            txtNumeroTarjetasAsociadas.setDisabled(false);
            txtTelefono.setDisabled(false);
            txtTipoDocumento.setDisabled(false);
            txtDocumento.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellidos.setValue(entity.getApellidos());
            txtApellidos.setDisabled(false);
            txtCiudad.setValue(entity.getCiudad());
            txtCiudad.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtNombres.setValue(entity.getNombres());
            txtNombres.setDisabled(false);
            txtNumeroTarjetasAsociadas.setValue(entity.getNumeroTarjetasAsociadas());
            txtNumeroTarjetasAsociadas.setDisabled(false);
            txtTelefono.setValue(entity.getTelefono());
            txtTelefono.setDisabled(false);
            txtTipoDocumento.setValue(entity.getTipoDocumento());
            txtTipoDocumento.setDisabled(false);
            txtDocumento.setValue(entity.getDocumento());
            txtDocumento.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedClientes = (ClientesDTO) (evt.getComponent().getAttributes()
                                             .get("selectedClientes"));
        txtApellidos.setValue(selectedClientes.getApellidos());
        txtApellidos.setDisabled(false);
        txtCiudad.setValue(selectedClientes.getCiudad());
        txtCiudad.setDisabled(false);
        txtDireccion.setValue(selectedClientes.getDireccion());
        txtDireccion.setDisabled(false);
        txtNombres.setValue(selectedClientes.getNombres());
        txtNombres.setDisabled(false);
        txtNumeroTarjetasAsociadas.setValue(selectedClientes.getNumeroTarjetasAsociadas());
        txtNumeroTarjetasAsociadas.setDisabled(false);
        txtTelefono.setValue(selectedClientes.getTelefono());
        txtTelefono.setDisabled(false);
        txtTipoDocumento.setValue(selectedClientes.getTipoDocumento());
        txtTipoDocumento.setDisabled(false);
        txtDocumento.setValue(selectedClientes.getDocumento());
        txtDocumento.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedClientes == null) && (entity == null)) {
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
            entity = new Clientes();

            Long documento = FacesUtils.checkLong(txtDocumento);

            entity.setApellidos(FacesUtils.checkString(txtApellidos));
            entity.setCiudad(FacesUtils.checkString(txtCiudad));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setDocumento(documento);
            entity.setNombres(FacesUtils.checkString(txtNombres));
            entity.setNumeroTarjetasAsociadas(FacesUtils.checkLong(
                    txtNumeroTarjetasAsociadas));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            entity.setTipoDocumento(FacesUtils.checkString(txtTipoDocumento));
            businessDelegatorView.saveClientes(entity);
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
                Long documento = new Long(selectedClientes.getDocumento());
                entity = businessDelegatorView.getClientes(documento);
            }

            entity.setApellidos(FacesUtils.checkString(txtApellidos));
            entity.setCiudad(FacesUtils.checkString(txtCiudad));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setNombres(FacesUtils.checkString(txtNombres));
            entity.setNumeroTarjetasAsociadas(FacesUtils.checkLong(
                    txtNumeroTarjetasAsociadas));
            entity.setTelefono(FacesUtils.checkString(txtTelefono));
            entity.setTipoDocumento(FacesUtils.checkString(txtTipoDocumento));
            businessDelegatorView.updateClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedClientes = (ClientesDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedClientes"));

            Long documento = new Long(selectedClientes.getDocumento());
            entity = businessDelegatorView.getClientes(documento);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long documento = FacesUtils.checkLong(txtDocumento);
            entity = businessDelegatorView.getClientes(documento);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteClientes(entity);
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

    public String action_modifyWitDTO(String apellidos, String ciudad,
        String direccion, Long documento, String nombres,
        Long numeroTarjetasAsociadas, String telefono, String tipoDocumento)
        throws Exception {
        try {
            entity.setApellidos(FacesUtils.checkString(apellidos));
            entity.setCiudad(FacesUtils.checkString(ciudad));
            entity.setDireccion(FacesUtils.checkString(direccion));
            entity.setNombres(FacesUtils.checkString(nombres));
            entity.setNumeroTarjetasAsociadas(FacesUtils.checkLong(
                    numeroTarjetasAsociadas));
            entity.setTelefono(FacesUtils.checkString(telefono));
            entity.setTipoDocumento(FacesUtils.checkString(tipoDocumento));
            businessDelegatorView.updateClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ClientesView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellidos() {
        return txtApellidos;
    }

    public void setTxtApellidos(InputText txtApellidos) {
        this.txtApellidos = txtApellidos;
    }

    public InputText getTxtCiudad() {
        return txtCiudad;
    }

    public void setTxtCiudad(InputText txtCiudad) {
        this.txtCiudad = txtCiudad;
    }

    public InputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtNombres() {
        return txtNombres;
    }

    public void setTxtNombres(InputText txtNombres) {
        this.txtNombres = txtNombres;
    }

    public InputText getTxtNumeroTarjetasAsociadas() {
        return txtNumeroTarjetasAsociadas;
    }

    public void setTxtNumeroTarjetasAsociadas(
        InputText txtNumeroTarjetasAsociadas) {
        this.txtNumeroTarjetasAsociadas = txtNumeroTarjetasAsociadas;
    }

    public InputText getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtTipoDocumento() {
        return txtTipoDocumento;
    }

    public void setTxtTipoDocumento(InputText txtTipoDocumento) {
        this.txtTipoDocumento = txtTipoDocumento;
    }

    public InputText getTxtDocumento() {
        return txtDocumento;
    }

    public void setTxtDocumento(InputText txtDocumento) {
        this.txtDocumento = txtDocumento;
    }

    public List<ClientesDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataClientes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ClientesDTO> clientesDTO) {
        this.data = clientesDTO;
    }

    public ClientesDTO getSelectedClientes() {
        return selectedClientes;
    }

    public void setSelectedClientes(ClientesDTO clientes) {
        this.selectedClientes = clientes;
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
