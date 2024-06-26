
package com.example.DBOperation1.controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DBOperation1.entity.User;
import com.example.DBOperation1.service.UserService;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	
	//api/users/createuser
	@Autowired
    private UserService userService;

	
	/*the data needs to be sent through postman in body
	 *  select raw and formatt of data as json
	 * 
	 * { "lastName": "walker", "firstName": "johni", "email": "abc1@gmail.com"
	 * 
	 * }
	 */
	//http://localhost:8080/api/users/createuser 
    // build create User REST API
    @PostMapping(path="/createuser", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user){
       User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
   
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody User user){
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
}
