package com.saturn.crm.utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Desc: Jwt 工具类
 * Author: Saturn_Wh
 * Date: 2025/6/25 14:01
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration")
    private long expiration;

    //生成 JWT 令牌：根据用户名生成一个有效期为 2 小时的 JWT 令牌。
    public String generateToken(String userName){
        return Jwts.builder()
                /**
                 * Subject 是 JWT 的标准声明之一，通常存储用户标识（如用户名或用户 ID）。
                 * 后续可以通过解析令牌提取 Subject 来获取用户名。
                 * 例如，如果 username = "john_doe"，则令牌的 Claims 中包含 "sub": "john_doe"。
                 */
                .setSubject(userName)
                /**
                 * setIssuedAt：设置令牌的发布时间
                 * setExpiration：设置令牌的过期时间
                 */
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                /**
                 * SignatureAlgorithm.HS256 指定使用 HMAC-SHA256 算法进行签名。
                 * SECRET 是签名的密钥，字符串会被编码为字节数组。
                 * 签名确保令牌未被篡改，解析时会用相同的密钥验证签名。
                 * HS256 是一种对称加密算法，签名和验证使用同一密钥（SECRET）。
                 */
                .signWith(SignatureAlgorithm.HS256,secret)
                //将配置好的 JWT 序列化为紧凑的字符串格式（标准的 JWT 格式）。
                .compact();
    }
    //    解析 JWT 令牌：从令牌中提取用户名。
    public String getUserNameFromToken(String token){
        //创建一个令牌解析器，然后调用setSigningKey方法,设置解析器的解析签名密钥。
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    //    验证令牌是否过期：检查令牌是否仍在有效期内。
    public boolean isTokenExpired(String token){
        //如果token的截止时间毫秒数比传入的时间（当前时间）小，则说明令牌过期了，返回true。
        return getExpirationDateFromToken(token).before(new Date());
    }
    //    获取令牌过期时间：从令牌中提取过期时间。
    public Date getExpirationDateFromToken(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
