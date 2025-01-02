package com.wsda.bikegarage.services;

import com.wsda.bikegarage.EmailSender;
import com.wsda.bikegarage.entities.Impiegato;
import com.wsda.bikegarage.repositories.ImpiegatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpiegatoService {

    @Autowired
    private ImpiegatoRepository impiegatoRepository;

    public Impiegato getImpiegato(String username, String password) {
        Impiegato impiegato = null;
        impiegato = impiegatoRepository.findImpiegatoByUsernameAndPassword(username, password);
        return impiegato;
    }

    public Impiegato getImpiegatoByMail(String mail) {
        Impiegato impiegato = null;
        impiegato = impiegatoRepository.findFirstImpiegatoByMail(mail);
        return  impiegato;
    }

    public String sendOTP(String mail) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int digit = (int) (Math.random() * 10);
            otp.append(digit);
        }
        String otpString = otp.toString();
        EmailSender.sendEmailCode(mail, otpString);
        return otpString;
    }

    public void setNewPasswordImpiegato(Impiegato impiegato, String newPassword) {
        impiegato.setPassword(newPassword);
        impiegatoRepository.save(impiegato);
    }
}
