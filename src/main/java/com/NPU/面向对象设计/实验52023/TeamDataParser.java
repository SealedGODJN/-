package com.NPU.面向对象设计.实验52023;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TeamDataParser extends JFrame {
    private JButton readButton;
    private JComboBox<String> teamIdComboBox;
    private JTextArea outputTextArea;
    private JButton plainTextButton;
    private JButton htmlButton;
    private JButton xmlButton;

    private Map<String, Team> teamMap;

    public TeamDataParser() {
        setTitle("Team Data Parser");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        readButton = new JButton("读取文件");
        teamIdComboBox = new JComboBox<>();
        outputTextArea = new JTextArea();
        plainTextButton = new JButton("Plain Text");
        htmlButton = new JButton("HTML");
        xmlButton = new JButton("XML");

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(readButton);
        topPanel.add(teamIdComboBox);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(plainTextButton);
        bottomPanel.add(htmlButton);
        bottomPanel.add(xmlButton);
        add(bottomPanel, BorderLayout.SOUTH);

        readButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    teamMap = readTeamData("D:\\team.dat");
                    populateTeamIds();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(TeamDataParser.this, "无法读取文件: team.dat");
                }
            }
        });

        plainTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamId = (String) teamIdComboBox.getSelectedItem();
                if (teamId != null) {
                    outputTextArea.setText(getTeamDataAsString(teamId, Format.PLAIN_TEXT));
                }
            }
        });

        htmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamId = (String) teamIdComboBox.getSelectedItem();
                if (teamId != null) {
                    outputTextArea.setText(getTeamDataAsString(teamId, Format.HTML));
                }
            }
        });

        xmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String teamId = (String) teamIdComboBox.getSelectedItem();
                if (teamId != null) {
                    outputTextArea.setText(getTeamDataAsString(teamId, Format.XML));
                }
            }
        });
    }

    private Map<String, Team> readTeamData(String filename) throws IOException {
        Map<String, Team> teamMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            Team team = parseTeam(line);
            if (team != null) {
                teamMap.put(team.getTeamId(), team);
            }
        }
        reader.close();
        return teamMap;
    }

    private Team parseTeam(String line) {
        String[] parts = line.split("_");
        if (parts.length < 11 || !parts[0].equals("Team")) {
            return null;
        }

        String teamId = parts[1];
        String teamName = parts[2];
        String department = parts[3];
        String creatorId = parts[5];
        String creatorName = parts[6];
        String creatorPhone = parts[7];
        String creatorEmail = parts[8];
        String creatorStudentNo = parts[9];
        String creatorGender = parts[10];
        String creatorGrade = parts[11];
        String creatorDepartment = parts[12];

        Student creator = new Student(creatorId, creatorName, creatorPhone, creatorEmail, creatorStudentNo, creatorGender, creatorGrade, creatorDepartment);

        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        for (int i = 13; i < parts.length; i += 8) {
            String role = parts[i];
            if (role.equals("Student")) {
                String studentId = parts[i + 1];
                String studentName = parts[i + 2];
                String studentPhone = parts[i + 3];
                String studentEmail = parts[i + 4];
                String studentNo = parts[i + 5];
                String studentGender = parts[i + 6];
                String studentGrade = parts[i + 7];
                String studentDepartment = parts[i + 8];
                Student student = new Student(studentId, studentName, studentPhone, studentEmail, studentNo, studentGender, studentGrade, studentDepartment);
                students.add(student);
            } else if (role.equals("Teacher")) {
                String teacherId = parts[i + 1];
                String teacherName = parts[i + 2];
                String teacherPhone = parts[i + 3];
                String teacherEmail = parts[i + 4];
                String teacherNo = parts[i + 5];
                String teacherDepartment = parts[i + 6];
                Teacher teacher = new Teacher(teacherId, teacherName, teacherPhone, teacherEmail, teacherNo, teacherDepartment);
                teachers.add(teacher);
            }
        }

        return new Team(teamId, teamName, department, creator, students, teachers);
    }

    private void populateTeamIds() {
        teamIdComboBox.removeAllItems();
        for (String teamId : teamMap.keySet()) {
            teamIdComboBox.addItem(teamId);
        }
    }

    private String getTeamDataAsString(String teamId, Format format) {
        Team team = teamMap.get(teamId);
        if (team == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        switch (format) {
            case PLAIN_TEXT:
                sb.append("团队信息：\n");
                sb.append("团队ID: ").append(team.getTeamId()).append("\n");
                sb.append("团队名称: ").append(team.getTeamName()).append("\n");
                sb.append("所属学院: ").append(team.getDepartment()).append("\n\n");
                sb.append("团队创建者信息：\n");
                sb.append("学生ID: ").append(team.getCreator().getId()).append("\n");
                sb.append("学生姓名: ").append(team.getCreator().getName()).append("\n");
                sb.append("学生电话: ").append(team.getCreator().getPhone()).append("\n");
                sb.append("学生邮箱: ").append(team.getCreator().getEmail()).append("\n");
                sb.append("学生学号: ").append(team.getCreator().getStudentNo()).append("\n");
                sb.append("学生性别: ").append(team.getCreator().getGender()).append("\n");
                sb.append("学生年级: ").append(team.getCreator().getGrade()).append("\n");
                sb.append("学生学院: ").append(team.getCreator().getDepartment()).append("\n\n");
                sb.append("团队成员信息：\n");
                for (Student student : team.getStudents()) {
                    sb.append("学生ID: ").append(student.getId()).append("\n");
                    sb.append("学生姓名: ").append(student.getName()).append("\n");
                    sb.append("学生电话: ").append(student.getPhone()).append("\n");
                    sb.append("学生邮箱: ").append(student.getEmail()).append("\n");
                    sb.append("学生学号: ").append(student.getStudentNo()).append("\n");
                    sb.append("学生性别: ").append(student.getGender()).append("\n");
                    sb.append("学生年级: ").append(student.getGrade()).append("\n");
                    sb.append("学生学院: ").append(student.getDepartment()).append("\n\n");
                }
                sb.append("指导教师信息：\n");
                for (Teacher teacher : team.getTeachers()) {
                    sb.append("教师ID: ").append(teacher.getId()).append("\n");
                    sb.append("教师姓名: ").append(teacher.getName()).append("\n");
                    sb.append("教师电话: ").append(teacher.getPhone()).append("\n");
                    sb.append("教师邮箱: ").append(teacher.getEmail()).append("\n");
                    sb.append("教师工号: ").append(teacher.getTeacherNo()).append("\n");
                    sb.append("教师学院: ").append(teacher.getDepartment()).append("\n\n");
                }
                break;
            case HTML:
                sb.append("<html><body>");
                sb.append("<h2>团队信息</h2>");
                sb.append("<b>团队ID:</b> ").append(team.getTeamId()).append("<br>");
                sb.append("<b>团队名称:</b> ").append(team.getTeamName()).append("<br>");
                sb.append("<b>所属学院:</b> ").append(team.getDepartment()).append("<br><br>");
                sb.append("<h3>团队创建者信息</h3>");
                sb.append("<b>学生ID:</b> ").append(team.getCreator().getId()).append("<br>");
                sb.append("<b>学生姓名:</b> ").append(team.getCreator().getName()).append("<br>");
                sb.append("<b>学生电话:</b> ").append(team.getCreator().getPhone()).append("<br>");
                sb.append("<b>学生邮箱:</b> ").append(team.getCreator().getEmail()).append("<br>");
                sb.append("<b>学生学号:</b> ").append(team.getCreator().getStudentNo()).append("<br>");
                sb.append("<b>学生性别:</b> ").append(team.getCreator().getGender()).append("<br>");
                sb.append("<b>学生年级:</b> ").append(team.getCreator().getGrade()).append("<br>");
                sb.append("<b>学生学院:</b> ").append(team.getCreator().getDepartment()).append("<br><br>");
                sb.append("<h3>团队成员信息</h3>");
                for (Student student : team.getStudents()) {
                    sb.append("<b>学生ID:</b> ").append(student.getId()).append("<br>");
                    sb.append("<b>学生姓名:</b> ").append(student.getName()).append("<br>");
                    sb.append("<b>学生电话:</b> ").append(student.getPhone()).append("<br>");
                    sb.append("<b>学生邮箱:</b> ").append(student.getEmail()).append("<br>");
                    sb.append("<b>学生学号:</b> ").append(student.getStudentNo()).append("<br>");
                    sb.append("<b>学生性别:</b> ").append(student.getGender()).append("<br>");
                    sb.append("<b>学生年级:</b> ").append(student.getGrade()).append("<br>");
                    sb.append("<b>学生学院:</b> ").append(student.getDepartment()).append("<br><br>");
                }
                sb.append("<h3>指导教师信息</h3>");
                for (Teacher teacher : team.getTeachers()) {
                    sb.append("<b>教师ID:</b> ").append(teacher.getId()).append("<br>");
                    sb.append("<b>教师姓名:</b> ").append(teacher.getName()).append("<br>");
                    sb.append("<b>教师电话:</b> ").append(teacher.getPhone()).append("<br>");
                    sb.append("<b>教师邮箱:</b> ").append(teacher.getEmail()).append("<br>");
                    sb.append("<b>教师工号:</b> ").append(teacher.getTeacherNo()).append("<br>");
                    sb.append("<b>教师学院:</b> ").append(teacher.getDepartment()).append("<br><br>");
                }
                sb.append("</body></html>");
                break;
            case XML:
                sb.append("<team>\n");
                sb.append("    <teamId>").append(team.getTeamId()).append("</teamId>\n");
                sb.append("    <teamName>").append(team.getTeamName()).append("</teamName>\n");
                sb.append("    <department>").append(team.getDepartment()).append("</department>\n");
                sb.append("    <creator>\n");
                sb.append("        <id>").append(team.getCreator().getId()).append("</id>\n");
                sb.append("        <name>").append(team.getCreator().getName()).append("</name>\n");
                sb.append("        <phone>").append(team.getCreator().getPhone()).append("</phone>\n");
                sb.append("        <email>").append(team.getCreator().getEmail()).append("</email>\n");
                sb.append("        <studentNo>").append(team.getCreator().getStudentNo()).append("</studentNo>\n");
                sb.append("        <gender>").append(team.getCreator().getGender()).append("</gender>\n");
                sb.append("        <grade>").append(team.getCreator().getGrade()).append("</grade>\n");
                sb.append("        <department>").append(team.getCreator().getDepartment()).append("</department>\n");
                sb.append("    </creator>\n");

                for (Student student : team.getStudents()) {
                    sb.append("    <student>\n");
                    sb.append("        <id>").append(student.getId()).append("</id>\n");
                    sb.append("        <name>").append(student.getName()).append("</name>\n");
                    sb.append("        <phone>").append(student.getPhone()).append("</phone>\n");
                    sb.append("        <email>").append(student.getEmail()).append("</email>\n");
                    sb.append("        <studentNo>").append(student.getStudentNo()).append("</studentNo>\n");
                    sb.append("        <gender>").append(student.getGender()).append("</gender>\n");
                    sb.append("        <grade>").append(student.getGrade()).append("</grade>\n");
                    sb.append("        <department>").append(student.getDepartment()).append("</department>\n");
                    sb.append("    </student>\n");
                }

                for (Teacher teacher : team.getTeachers()) {
                    sb.append("    <teacher>\n");
                    sb.append("        <id>").append(teacher.getId()).append("</id>\n");
                    sb.append("        <name>").append(teacher.getName()).append("</name>\n");
                    sb.append("        <phone>").append(teacher.getPhone()).append("</phone>\n");
                    sb.append("        <email>").append(teacher.getEmail()).append("</email>\n");
                    sb.append("        <teacherNo>").append(teacher.getTeacherNo()).append("</teacherNo>\n");
                    sb.append("        <department>").append(teacher.getDepartment()).append("</department>\n");
                    sb.append("    </teacher>\n");
                }
                sb.append("</team>");
                break;
        }

        return sb.toString();
    }

    private enum Format {
        PLAIN_TEXT,
        HTML,
        XML
    }

    private class Team {
        private String teamId;
        private String teamName;
        private String department;
        private Student creator;
        private List<Student> students;
        private List<Teacher> teachers;

        public Team(String teamId, String teamName, String department, Student creator, List<Student> students, List<Teacher> teachers) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.department = department;
            this.creator = creator;
            this.students = students;
            this.teachers = teachers;
        }

        public String getTeamId() {
            return teamId;
        }

        public String getTeamName() {
            return teamName;
        }

        public String getDepartment() {
            return department;
        }

        public Student getCreator() {
            return creator;
        }

        public List<Student> getStudents() {
            return students;
        }

        public List<Teacher> getTeachers() {
            return teachers;
        }
    }

    private class Student {
        private String id;
        private String name;
        private String phone;
        private String email;
        private String studentNo;
        private String gender;
        private String grade;
        private String department;

        public Student(String id, String name, String phone, String email, String studentNo, String gender, String grade, String department) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.studentNo = studentNo;
            this.gender = gender;
            this.grade = grade;
            this.department = department;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getStudentNo() {
            return studentNo;
        }

        public String getGender() {
            return gender;
        }

        public String getGrade() {
            return grade;
        }

        public String getDepartment() {
            return department;
        }
    }

    private class Teacher {
        private String id;
        private String name;
        private String phone;
        private String email;
        private String teacherNo;
        private String department;

        public Teacher(String id, String name, String phone, String email, String teacherNo, String department) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.teacherNo = teacherNo;
            this.department = department;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getTeacherNo() {
            return teacherNo;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeamDataParser().setVisible(true);
            }
        });
    }
}
