package org.example.GUI;


import org.example.Daos.StudentDAO;
import org.example.Models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGUI extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField majorField;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private final StudentDAO studentDAO;

    public StudentGUI() {

        studentDAO = new StudentDAO();

        setTitle("Student Management");

        setSize(700, 500);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // Form
        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        nameField = new JTextField();
        ageField = new JTextField();
        majorField = new JTextField();

        formPanel.add(
                new JLabel("Name:")
        );

        formPanel.add(nameField);

        formPanel.add(
                new JLabel("Age:")
        );

        formPanel.add(ageField);

        formPanel.add(
                new JLabel("Major:")
        );

        formPanel.add(majorField);

        // Buttons
        JButton addButton =
                new JButton("Add");

        JButton updateButton =
                new JButton("Update");

        JButton deleteButton =
                new JButton("Delete");

        JButton clearButton =
                new JButton("Clear");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Table
        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Major"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );

        studentTable =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(studentTable);

        // Actions
        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        studentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    int row =
                            studentTable
                                    .getSelectedRow();

                    if (row != -1) {

                        nameField.setText(
                                tableModel
                                        .getValueAt(
                                                row,
                                                1
                                        )
                                        .toString()
                        );

                        ageField.setText(
                                tableModel
                                        .getValueAt(
                                                row,
                                                2
                                        )
                                        .toString()
                        );

                        majorField.setText(
                                tableModel
                                        .getValueAt(
                                                row,
                                                3
                                        )
                                        .toString()
                        );
                    }
                });

        setLayout(
                new BorderLayout()
        );

        add(
                formPanel,
                BorderLayout.NORTH
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        loadStudents();

        setVisible(true);
    }

    private void addStudent() {

        try {

            String name =
                    nameField.getText();

            int age =
                    Integer.parseInt(
                            ageField.getText()
                    );

            String major =
                    majorField.getText();

            if (
                    name.isEmpty()
                            || major.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields."
                );

                return;
            }

            Student student =
                    new Student(
                            name,
                            age,
                            major
                    );

            studentDAO.addStudent(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

            clearFields();

            loadStudents();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number."
            );
        }
    }

    private void updateStudent() {

        int row =
                studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            tableModel
                                    .getValueAt(
                                            row,
                                            0
                                    )
                                    .toString()
                    );

            String name =
                    nameField.getText();

            int age =
                    Integer.parseInt(
                            ageField.getText()
                    );

            String major =
                    majorField.getText();

            Student student =
                    new Student(
                            id,
                            name,
                            age,
                            major
                    );

            studentDAO.updateStudent(
                    student
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully!"
            );

            clearFields();

            loadStudents();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number."
            );
        }
    }

    private void deleteStudent() {

        int row =
                studentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        int id =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm ==
                        JOptionPane.YES_OPTION
        ) {

            studentDAO.deleteStudent(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted."
            );

            clearFields();

            loadStudents();
        }
    }

    private void loadStudents() {

        tableModel.setRowCount(0);

        List<Student> students =
                studentDAO.getAllStudents();

        for (Student student : students) {

            tableModel.addRow(
                    new Object[]{
                            student.getId(),
                            student.getName(),
                            student.getAge(),
                            student.getMajor()
                    }
            );
        }
    }

    private void clearFields() {

        nameField.setText("");
        ageField.setText("");
        majorField.setText("");

        studentTable.clearSelection();
    }
}
