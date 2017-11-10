package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.api.APIResponse;
import com.example.auth.AuthenticationFailedException;
import com.example.model.User;
import com.example.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	private static Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public @ResponseBody APIResponse createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		List<String> erros = new ArrayList<>();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			erros.add("There is already a user registered with the email provided");
		}
	
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		
	HashMap<String, Object> authResp = new HashMap<>();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	Object token = auth.getCredentials();
	authResp.put("token", token);
	authResp.put("user", user);
	authResp.put("Error", erros);
    

    return APIResponse.toOkResponse(authResp);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public @ResponseBody APIResponse home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
	//	Validate.isTrue(StringUtils.isNotBlank(user.getEmail()), "Email is blank");
      //  Validate.isTrue(StringUtils.isNotBlank(user.getEncryptedPassword()), "Encrypted password is blank");
        String password = null;
		try {
			password = decryptPassword(user);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        LOG.info("Looking for user by email: "+user.getEmail());


        HashMap<String, Object> authResp = new HashMap<>();
        if(userService.isValidPass(user, password)) {
        	ArrayList<String> erros = new ArrayList<>();
        	erros.add("User authenticated: "+user.getEmail());
            LOG.info("User authenticated: "+user.getEmail());
            HttpServletRequest request = null;
            userService.loginUser(user, request);
            createAuthResponse(user, authResp,erros);
        } else {
            try {
				throw new AuthenticationFailedException("Invalid username/password combination");
			} catch (AuthenticationFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return APIResponse.toOkResponse(authResp);
	}
	
	private void createAuthResponse(User user, HashMap<String, Object> authResp,ArrayList<String> erros) {
        String token = "";
        		//Jwts.builder().setSubject(user.getEmail())
               // .claim("role", user.getRole().name()).setIssuedAt(new Date())
              // .signWith(SignatureAlgorithm.HS256, JWTTokenAuthFilter.JWT_KEY).compact();
        authResp.put("token", token);
        authResp.put("user", user);
        authResp.put("Error", erros);
    }
	
	private String decryptPassword(User userDTO) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String passPhrase = "biZndDtCMkdeP8K0V15OKMKnSi85";
        String salt = userDTO.getSalt();
        String iv = userDTO.getIv();
        int iterationCount = userDTO.getIterations();
        int keySize = userDTO.getKeySize();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(passPhrase.toCharArray(), hex(salt), iterationCount, keySize);
        SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(hex(iv)));
        byte[] decrypted = cipher.doFinal(base64(userDTO.getPassword()));

        return new String(decrypted, "UTF-8");
    }

    private String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    private byte[] base64(String str) {
        return Base64.decodeBase64(str);
    }

    private String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    private byte[] hex(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        }
        catch (DecoderException e) {
            throw new IllegalStateException(e);
        }
    }
	

}
