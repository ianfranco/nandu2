/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.models;

import com.areatecnica.sigf.entities.SerieBoletoGuia;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author ianfr
 */
public class SerieBoletoGuiaDataModel extends ListDataModel<SerieBoletoGuia> implements SelectableDataModel<SerieBoletoGuia> {

    public SerieBoletoGuiaDataModel() {
    }

    public SerieBoletoGuiaDataModel(List<SerieBoletoGuia> list) {
        super(list);
    }

    @Override
    public Object getRowKey(SerieBoletoGuia object) {
        return object.getSerieBoletoGuiaId();
    }

    @Override
    public SerieBoletoGuia getRowData(String rowKey) {
        List<SerieBoletoGuia> items = (List<SerieBoletoGuia>) getWrappedData();

        for (SerieBoletoGuia e : items) {
            if (e.getSerieBoletoGuiaId().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

}
