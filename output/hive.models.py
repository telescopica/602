from django.db import models


class Contact(models.Model):
    name = models.CharField(max_length=30)
    isACompany = models.(default=False)
    mobile = models.CharField(max_length=16, blank=True, null=True)
    phone = models.CharField(max_length=16, blank=True, null=True)
    email = models.EmailField()
    address = models.CharField(max_length=255)
    docType = models.CharField(choices=DOCUMENT_TYPES)
    docNumber = models.CharField(max_length=30)
    url = models.URLField(blank=True, verify_exists=False, null=True)
    country = models.ForeignKey('Country')
    city = models.ForeignKey('City')



class Country(models.Model):
    name = models.CharField(max_length=64)
    countryCode = models.CharField(max_length=2)



class Court(models.Model):
    gameType = models.CharField(max_length=20, choices=GAME_TYPES, default=FUTBOL5)
    width = models.IntegerField(blank=True, null=True)
    length = models.IntegerField(blank=True, null=True)
    courtType = models.CharField(max_length=20, choices=COURT_TYPES, default="Sintetica")
    contact = models.ForeignKey('Contact')



class City(models.Model):
    name = models.CharField(max_length=64)
    country = models.ForeignKey('Country')



