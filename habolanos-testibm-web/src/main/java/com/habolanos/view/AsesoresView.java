package com.habolanos.view;

import com.habolanos.dto.AsesoresDTO;

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
public class AsesoresView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(AsesoresView.class);
    private InputText txtApellidos;
    private InputText txtEspecialidad;
    private InputText txtNombres;
    private InputText txtIdAsesor;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AsesoresDTO> data;
    private AsesoresDTO selectedAsesores;
    private Asesores entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{businessDelegator}")
    private BusinessDelegator businessDelegatorView;

    public AsesoresView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedAsesores = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAsesores = null;

        if (txtApellidos != null) {
            txtApellidos.setValue(null);
            txtApellidos.setDisabled(true);
        }

        if (txtEspecialidad != null) {
            txtEspecialidad.setValue(null);
            txtEspecialidad.setDisabled(true);
        }

        if (txtNombres != null) {
            txtNombres.setValue(null);
            txtNombres.setDisabled(true);
        }

        if (txtIdAsesor != null) {
            txtIdAsesor.setValue(null);
            txtIdAsesor.setDisabled(false);
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
            Long idAsesor = FacesUtils.checkLong(txtIdAsesor);
            entity = (idAsesor != null)
                ? businessDelegatorView.getAsesores(idAsesor) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellidos.setDisabled(false);
            txtEspecialidad.setDisabled(false);
            txtNombres.setDisabled(false);
            txtIdAsesor.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellidos.setValue(entity.getApellidos());
            txtApellidos.setDisabled(false);
            txtEspecialidad.setValue(entity.getEspecialidad());
            txtEspecialidad.setDisabled(false);
            txtNombres.setValue(entity.getNombres());
            txtNombres.setDisabled(false);
            txtIdAsesor.setValue(entity.getIdAsesor());
            txtIdAsesor.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAsesores = (AsesoresDTO) (evt.getComponent().getAttributes()
                                             .get("selectedAsesores"));
        txtApellidos.setValue(selectedAsesores.getApellidos());
        txtApellidos.setDisabled(false);
        txtEspecialidad.setValue(selectedAsesores.getEspecialidad());
        txtEspecialidad.setDisabled(false);
        txtNombres.setValue(selectedAsesores.getNombres());
        txtNombres.setDisabled(false);
        txtIdAsesor.setValue(selectedAsesores.getIdAsesor());
        txtIdAsesor.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAsesores == null) && (entity == null)) {
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
            entity = new Asesores();

            Long idAsesor = FacesUtils.checkLong(txtIdAsesor);

            entity.setApellidos(FacesUtils.checkString(txtApellidos));
            entity.setEspecialidad(FacesUtils.checkString(txtEspecialidad));
            entity.setIdAsesor(idAsesor);
            entity.setNombres(FacesUtils.checkString(txtNombres));
            businessDelegatorView.saveAsesores(entity);
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
                Long idAsesor = new Long(selectedAsesores.getIdAsesor());
                entity = businessDelegatorView.getAsesores(idAsesor);
            }

            entity.setApellidos(FacesUtils.checkString(txtApellidos));
            entity.setEspecialidad(FacesUtils.checkString(txtEspecialidad));
            entity.setNombres(FacesUtils.checkString(txtNombres));
            businessDelegatorView.updateAsesores(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAsesores = (AsesoresDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedAsesores"));

            Long idAsesor = new Long(selectedAsesores.getIdAsesor());
            entity = businessDelegatorView.getAsesores(idAsesor);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAsesor = FacesUtils.checkLong(txtIdAsesor);
            entity = businessDelegatorView.getAsesores(idAsesor);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAsesores(entity);
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

    public String action_modifyWitDTO(String apellidos, String especialidad,
        Long idAsesor, String nombres) throws Exception {
        try {
            entity.setApellidos(FacesUtils.checkString(apellidos));
            entity.setEspecialidad(FacesUtils.checkString(especialidad));
            entity.setNombres(FacesUtils.checkString(nombres));
            businessDelegatorView.updateAsesores(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AsesoresView").requestRender();
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

    public InputText getTxtEspecialidad() {
        return txtEspecialidad;
    }

    public void setTxtEspecialidad(InputText txtEspecialidad) {
        this.txtEspecialidad = txtEspecialidad;
    }

    public InputText getTxtNombres() {
        return txtNombres;
    }

    public void setTxtNombres(InputText txtNombres) {
        this.txtNombres = txtNombres;
    }

    public InputText getTxtIdAsesor() {
        return txtIdAsesor;
    }

    public void setTxtIdAsesor(InputText txtIdAsesor) {
        this.txtIdAsesor = txtIdAsesor;
    }

    public List<AsesoresDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAsesores();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AsesoresDTO> asesoresDTO) {
        this.data = asesoresDTO;
    }

    public AsesoresDTO getSelectedAsesores() {
        return selectedAsesores;
    }

    public void setSelectedAsesores(AsesoresDTO asesores) {
        this.selectedAsesores = asesores;
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
