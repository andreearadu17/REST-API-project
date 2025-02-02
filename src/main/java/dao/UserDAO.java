/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

import model.QuizCreator;

public interface UserDAO {
    void createUser(QuizCreator user);

    void insertUser(QuizCreator user);

    List<QuizCreator> getAllUsers();

    QuizCreator getUserByUsername(String username);

}
