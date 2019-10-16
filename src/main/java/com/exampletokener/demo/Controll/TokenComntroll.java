package com.exampletokener.demo.Controll;

//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import com.exampletokener.demo.Exception.ResourceNotFoundException;
import com.exampletokener.demo.Model.IdToken;
import com.exampletokener.demo.Model.Token;
import com.exampletokener.demo.Repo.IdTokenRepo;
import com.exampletokener.demo.Repo.TokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.SignatureAlgorithm;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/token")
public class TokenComntroll {

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    public IdTokenRepo idTokenRepo;

    public final int JWT_TOKEN_VALIDITY=60*60;

    private String auth="auth";
    private String scope="jj";


    @RequestMapping(path = "/hello")
    public String hi()
    {
        return "hi";
    }

    //create token
    public String generateToken(@RequestBody int id)
    {
        String jwt = Jwts.builder()
                .setSubject(auth)
                .setIssuedAt(new Date((System.currentTimeMillis()/(1000*60*60))))
                .setExpiration(new Date((System.currentTimeMillis()/(1000*60*60)) + 1))
                .claim("userId", Integer.toString(id))
                .claim("scope", scope)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "appSecret"
                )
                .compact();


        return jwt;



    }

    // Token save method
    @PostMapping("/idtokengetall")
    public IdToken idtokengetall(@Valid @RequestBody  IdToken idtoken) {
      //  String to=generateToken(id);

        return idTokenRepo.save(idtoken);
    }

    @RequestMapping(path = "/gettoken")
    public String createtokenandsaveit(@RequestBody int id,IdToken idToken)
    {
        String to=generateToken(id);

        idToken.setToken(to);
        idToken.setId(id);
        idtokengetall(idToken);


        return to;

    }





    private Jws<Claims> getclaimfromtoken(String token) {

        return Jwts.parser().setSigningKey("appSecret").parseClaimsJws(token);
    }




    @GetMapping("/verifytoken")
    public boolean verifi(@RequestBody int userid)
    {
        String nowtoken=generateToken(userid);

        IdToken top=idTokenRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User", "id", userid));
        String lasttoken=top.getToken();



        if(lasttoken.equals(nowtoken))

        {
           Jws<Claims> claimsJws=getclaimfromtoken(nowtoken);
            if(claimsJws.getBody().get("scope").equals(scope) && claimsJws.getBody().getSubject().equals(auth))
            {
                return true;
            }
           return true;
        }
        else  {
            return false;
        }


    }


    @PostMapping(path = "/ch")
    public String verifit(@RequestBody int userid) {
        String nowtoken = generateToken(userid);
        return nowtoken;

    }


  /*  @RequestMapping(path = "/valid")
    public boolean validate(String token,int id)
    {

        Jws<Claims> claimsJws=getclaimfromtoken(token);
        if(claimsJws.getBody().get("scope").equals(scope) && claimsJws.getBody().getSubject().equals(auth)
                &&  claimsJws.getBody().get("id").equals(Integer.toString(id)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/

      /* @RequestMapping(path = "/getclaims")
    public Jws<Claims> getclaimfromtoken(String token, int userid)
    {

        return Jwts.parser().setSigningKey("appSecret").parseClaimsJws(token);

    }*/


   /* public boolean databasevalidation(String token)
    {
        Jws<Claims> claimsJws=getclaimfromtoken(token);
        return true;

    }*/


   /* public String getStoredPw(int id){
        System.out.println("Get stored pw method started.");
        Token res = tokenRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return res.getPassword();
    }*/



   /* @GetMapping(path = "/pass")
    public List<IdToken> getoneidfromdatabase() {
        return (List<IdToken>) idTokenRepo.findAll();
    }*/









    @GetMapping("/useraa/{id}")
    public String getNoteyId(@PathVariable(value = "id") int id)
    {
        Token re=tokenRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return re.getName();
    }

    @PostMapping("/notes")
    public Token createNote(@Valid @RequestBody Token token) {
        return tokenRepo.save(token);
    }

    @GetMapping(path = "/passs")
    public List<IdToken> getidfromdatabase() {
        return (List<IdToken>) idTokenRepo.findAll();
    }


    @RequestMapping(path = "/savename")
    public Token addToken(Token token)
    {
        return  tokenRepo.save(token);

    }

    @GetMapping(path = "/getall")
    public List<Token> getAll()
    {
        return (List<Token>) tokenRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<Token> getNoteById(@PathVariable(value = "id") int id)
    {
        return tokenRepo.findById(id);
    }

    @GetMapping(path = "/pass")
    public List<IdToken> getidfromidtoken() {
        return (List<IdToken>) idTokenRepo.findAll();
    }

}
