package com.yaps.petstore.client.ui.referential;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.yaps.petstore.client.ui.AbstractFrame;
import com.yaps.petstore.client.ui.referential.bar.BarEventListener;
import com.yaps.petstore.client.ui.referential.bar.CloseEvent;
import com.yaps.petstore.client.ui.referential.bar.CreateEvent;
import com.yaps.petstore.client.ui.referential.bar.DeleteEvent;
import com.yaps.petstore.client.ui.referential.bar.FindEvent;
import com.yaps.petstore.client.ui.referential.bar.ManageBar;
import com.yaps.petstore.client.ui.referential.bar.ResetEvent;
import com.yaps.petstore.client.ui.referential.bar.UpdateEvent;
import com.yaps.petstore.common.delegate.CatalogDelegate;
import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.DuplicateKeyException;
import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.common.logging.Trace;

public class ManageProductFrame extends AbstractFrame implements BarEventListener {
	// Variables declaration
    private final JLabel labelTitle = new JLabel();
    private final JPanel panelCenter = new JPanel();
    private final JTextField textId = new JTextField();
    private final JTextField textName = new JTextField();
    private final JTextField textDescription = new JTextField();
    private final JTextField textCategoryId = new JTextField();
    private final JTextField textCategoryName = new JTextField();

    private final ManageBar manageBar = new ManageBar();

    /**
     * Creates new form
     */
    public ManageProductFrame() {
        initComponents();
        pack();
        manageBar.setManageListener(this);
    }

    // This method is called from within the constructor to display all the graphical components
    private final void initComponents() {
        // Panel North
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", 1, 18));
        labelTitle.setText("Product");

        getContentPane().add(labelTitle, BorderLayout.NORTH);

        // Panel Center
        panelCenter.setLayout(new GridLayout(3, 2));

        panelCenter.add(new JLabel("Identifier"));
        panelCenter.add(textId);

        panelCenter.add(new JLabel("Name"));
        panelCenter.add(textName);

        panelCenter.add(new JLabel("Description"));
        panelCenter.add(textDescription);
        
        panelCenter.add(new JLabel("CategoryId"));
        panelCenter.add(textCategoryId);
        
        panelCenter.add(new JLabel("CategoryName"));
        panelCenter.add(textCategoryName);

        getContentPane().add(panelCenter, BorderLayout.CENTER);

        // Panel South
        getContentPane().add(manageBar, BorderLayout.SOUTH);
    }

    public void create(final CreateEvent evt) {
        final String mname = "create";

        // Sets all the Category data
        final ProductDTO productDTO = new ProductDTO(textId.getText(), textName.getText(), textDescription.getText());
        productDTO.setCategoryId(textCategoryId.getText());
        productDTO.setCategoryName(textCategoryName.getText());
        try {
            // Creates the category
            CatalogDelegate.createProduct(productDTO);

        } catch (DuplicateKeyException e) {
            JOptionPane.showMessageDialog(this, "This Id already exists", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot create the product", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
    }

    public void delete(final DeleteEvent evt) {
        final String mname = "delete";

        try {
            // Deletes the category
            CatalogDelegate.deleteProduct(textId.getText());
            reset(new ResetEvent(this));

        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot delete the product", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
    }

    public void find(final FindEvent evt) {
        final String mname = "find";

        try {
            // Finds the product
            final ProductDTO productDTO = CatalogDelegate.findProduct(textId.getText());

            // Displays the data
            textId.setText(productDTO.getId());
            textName.setText(productDTO.getName());
            textDescription.setText(productDTO.getDescription());
            textCategoryId.setText(productDTO.getCategoryId());
            textCategoryName.setText(productDTO.getCategoryName());

        } catch (ObjectNotFoundException e) {
            JOptionPane.showMessageDialog(this, "This product has not been found", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot find the product", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
    }

    public void update(final UpdateEvent evt) {
        final String mname = "update";

        // Sets all the Category data
        final ProductDTO productDTO = new ProductDTO(textId.getText(), textName.getText(), textDescription.getText());
        productDTO.setCategoryId(textCategoryId.getText());
        productDTO.setCategoryName(textCategoryName.getText());
        
        try {
            // Updates the category
            CatalogDelegate.updateProduct(productDTO);

        } catch (CheckException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot update the product", "Error", JOptionPane.ERROR_MESSAGE);
            Trace.throwing(getCname(), mname, e);
        }
    }

    public void reset(final ResetEvent evt) {
        textDescription.setText("");
        textId.setText("");
        textName.setText("");
        textCategoryId.setText("");
        textCategoryName.setText("");
    }

    public void close(final CloseEvent evt) {
        dispose();
    }
	
}
