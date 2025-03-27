; a2-signalling.asm
; CSC 230: Fall 2022
;
; Student name: Mahdi Haji Hosseini
; Student ID: V00968642
; Date of completed work:
;
; *******************************
; Code provided for Assignment #2
;
; Author: Mike Zastre (2022-Oct-15)
;
 
; This skeleton of an assembly-language program is provided to help you
; begin with the programming tasks for A#2. As with A#1, there are "DO
; NOT TOUCH" sections. You are *not* to modify the lines within these
; sections. The only exceptions are for specific changes changes
; announced on Brightspace or in written permission from the course
; instructor. *** Unapproved changes could result in incorrect code
; execution during assignment evaluation, along with an assignment grade
; of zero. ****

.include "m2560def.inc"
.cseg
.org 0

; ***************************************************
; **** BEGINNING OF FIRST "STUDENT CODE" SECTION ****
; ***************************************************

	; initializion code will need to appear in this
    ; section

	ldi r16, 0xFF
	sts DDRL, r16
	out DDRB, r16


	ldi r16, HIGH(RAMEND)
	out SPH, r16
	ldi r16, LOW(RAMEND)
	out SPL, r16
 




; ***************************************************
; **** END OF FIRST "STUDENT CODE" SECTION **********
; ***************************************************

; ---------------------------------------------------
; ---- TESTING SECTIONS OF THE CODE -----------------
; ---- TO BE USED AS FUNCTIONS ARE COMPLETED. -------
; ---------------------------------------------------
; ---- YOU CAN SELECT WHICH TEST IS INVOKED ---------
; ---- BY MODIFY THE rjmp INSTRUCTION BELOW. --------
; -----------------------------------------------------

	rjmp test_part_d
	; Test code


test_part_a:
	ldi r16, 0b00100001
	rcall set_leds
	rcall delay_long

	clr r16
	rcall set_leds
	rcall delay_long

	ldi r16, 0b00111000
	rcall set_leds
	rcall delay_short

	clr r16
	rcall set_leds
	rcall delay_long

	ldi r16, 0b00100001
	rcall set_leds
	rcall delay_long

	clr r16
	rcall set_leds

	rjmp end


test_part_b:
	ldi r17, 0b00101010
	rcall slow_leds
	ldi r17, 0b00010101
	rcall slow_leds
	ldi r17, 0b00101010
	rcall slow_leds
	ldi r17, 0b00010101
	rcall slow_leds

	rcall delay_long
	rcall delay_long

	ldi r17, 0b00101010
	rcall fast_leds
	ldi r17, 0b00010101
	rcall fast_leds
	ldi r17, 0b00101010
	rcall fast_leds
	ldi r17, 0b00010101
	rcall fast_leds
	ldi r17, 0b00101010
	rcall fast_leds
	ldi r17, 0b00010101
	rcall fast_leds
	ldi r17, 0b00101010
	rcall fast_leds
	ldi r17, 0b00010101
	rcall fast_leds

	rjmp end

test_part_c:
	ldi r16, 0b11111000
	push r16
	rcall leds_with_speed
	pop r16

	ldi r16, 0b11011100
	push r16
	rcall leds_with_speed
	pop r16

	ldi r20, 0b00100000
test_part_c_loop:
	push r20
	rcall leds_with_speed
	pop r20
	lsr r20
	brne test_part_c_loop

	rjmp end


test_part_d:
	ldi r21, 'E'
	push r21
	rcall encode_letter
	pop r21
	push r25
	rcall leds_with_speed
	pop r25

	rcall delay_long

	ldi r21, 'A'
	push r21
	rcall encode_letter
	pop r21
	push r25
	rcall leds_with_speed
	pop r25

	rcall delay_long


	ldi r21, 'M'
	push r21
	rcall encode_letter
	pop r21
	push r25
	rcall leds_with_speed
	pop r25

	rcall delay_long

	ldi r21, 'H'
	push r21
	rcall encode_letter
	pop r21
	push r25
	rcall leds_with_speed
	pop r25

	rcall delay_long

	rjmp end


test_part_e:
	ldi r25, HIGH(WORD02 << 1)
	ldi r24, LOW(WORD02 << 1)
	rcall display_message
	rjmp end

end:
    rjmp end






; ****************************************************
; **** BEGINNING OF SECOND "STUDENT CODE" SECTION ****
; ****************************************************

set_leds:
	
	; Register usage:
    ; r16 - input value determining which LEDs to turn on
    ; r17 - working register for preparing the value for PORTL
    ; r18 - working register for preparing the value for PORTB

    ; Clear r17 and r18 as they will hold the values for PORTL and PORTB respectively
    clr r17
    clr r18

    ; Prepare the value for PORTL. We're mapping bits 5-0 of r16 to bits 7, 5, 3, and 1 of PORTL, but in reverse order.
    ; Check each bit of r16 and set the appropriate bit of PORTL.
    sbrc r16, 0  ; Changed the order here
    ori r17, (1 << 7)  ; If bit 0 of r16 is set, set bit 7 of PORTL
    sbrc r16, 1  ; Changed the order here
    ori r17, (1 << 5)  ; If bit 1 of r16 is set, set bit 5 of PORTL
    sbrc r16, 2  ; Changed the order here
    ori r17, (1 << 3)  ; If bit 2 of r16 is set, set bit 3 of PORTL
    sbrc r16, 3  ; Changed the order here
    ori r17, (1 << 1)  ; If bit 3 of r16 is set, set bit 1 of PORTL

    ; Write the prepared value to PORTL
    sts PORTL, r17  

    ; Prepare the value for PORTB. We're mapping bits 5-4 of r16 to bits 3 and 1 of PORTB, but in reverse order.
    sbrc r16, 4  ; Changed the order here
    ori r18, (1 << 3)  ; If bit 4 of r16 is set, set bit 3 of PORTB
    sbrc r16, 5  ; Changed the order here
    ori r18, (1 << 1)  ; If bit 5 of r16 is set, set bit 1 of PORTB

    ; Write the prepared value to PORTB
    out PORTB, r18  

    
    ret      

	

slow_leds:
	

	mov r16, r17
	rcall set_leds
	rcall delay_long
	
	ldi r16, 0x00
	rcall set_leds

    
    ret      

	


fast_leds:

	mov r16, r17
	rcall set_leds
	rcall delay_short

	ldi r16, 0x00
	rcall set_leds

	ret


leds_with_speed:


	
	
	cpi r25, 0x00  ; check for part d condition
	breq part_c_in_place
	mov r20, r25
	part_c_in_place:
		; Check if r20 is 0x00
		cpi r20, 0x00
		breq use_r16_as_source
		mov r17, r20
		rjmp check_speed_bits

	use_r16_as_source:
		; Copy r16 to r17
		mov r17, r16


	check_speed_bits:
		; Test the 7th bit of r17 (0-based index)
		sbrs r17, 7  ; If 7th bit is set, skip the next instruction
		rjmp maybe_fast  ; If 7th bit isn't set, check if the pattern is 00xxxxxx

		; If we're here, the 7th bit is set, now check the 6th bit
		sbrs r17, 6  ; If 6th bit is set, skip the next instruction
		ret          ; If 6th bit isn't set, it's 10xxxxxx which we ignore

		
		; 11xxxxxx pattern
		rcall slow_leds
		ret

	maybe_fast:
		; Here, we are potentially dealing with 00xxxxxx pattern
		sbrs r17, 6  ; If 6th bit is set, return since it's 01xxxxxx
		cpi r23, 0x01 ; check for part d condition
		breq slow_leds
		rcall fast_leds
		ret






; Note -- this function will only ever be tested
; with upper-case letters, but it is a good idea
; to anticipate some errors when programming (i.e. by
; accidentally putting in lower-case letters). Therefore
; the loop does explicitly check if the hyphen/dash occurs,
; in which case it terminates with a code not found
; for any legal letter.

encode_letter:

	push ZH
	push ZL

    ; Initialize Z pointer to start of PATTERNS
	ldi ZH, HIGH(PATTERNS<<1)  ; ZH
    ldi ZL, LOW(PATTERNS<<1)   ; ZL
    

	
	clr r25

search_loop:
    ; Load the next letter from PATTERNS into r23
    lpm r23, Z

	
    ; Compare the letter in r23 with the one in r21
    cp r21, r23
    breq process_pattern  ; If they match, we've found the letter
	adiw Z, 8
    ; If not found, move Z pointer 8 bytes ahead (1 for the letter, 6 for the pattern, 1 for the duration)
    
    rjmp search_loop            ; Continue search


process_pattern:
		adiw Z, 1
		lpm r23, Z    ; Load next pattern character
		cpi r23, 0x6f   ; Compare with 'o'
		breq bit_changer1
		rjmp next1
		bit_changer1:
			ori r25, 0b00100000
			rjmp next1

		next1:
			adiw Z, 1
			lpm r23, Z    ; Load next pattern character
			cpi r23, 0x6f   ; Compare with 'o'
			breq bit_changer2
			rjmp next2
		bit_changer2:
			ori r25, 0b00010000
			rjmp next2

		next2:
			adiw Z, 1
			lpm r23, Z    ; Load next pattern character
			cpi r23, 0x6f   ; Compare with 'o'
			breq bit_changer3
			rjmp next3
		bit_changer3:
			ori r25, 0b00001000
			rjmp next3

		next3:
			adiw Z, 1
			lpm r23, Z   ; Load next pattern character
			cpi r23, 0x6f   ; Compare with 'o'
			breq bit_changer4
			rjmp next4
		bit_changer4:
			ori r25, 0b00000100
			rjmp next4

		next4:
			adiw Z, 1
			lpm r23, Z    ; Load next pattern character
			cpi r23, 0x6f   ; Compare with 'o'
			breq bit_changer5
			rjmp next5
		bit_changer5:
			ori r25, 0b00000010
			rjmp next5

		next5:
			adiw Z, 1
			lpm r23, Z    ; Load next pattern character
			cpi r23, 0x6f   ; Compare with 'o'
			breq bit_changer6
			rjmp next6
		bit_changer6:
			ori r25, 0b00000001
			rjmp next6

		next6:
			adiw Z, 1
			lpm r23, Z  ;store speed in r23
			pop ZL
			pop ZH

		ret
	 
display_message:
	
	push r25
    push r24
	push r23
	push r21
	push ZH
	push ZL

	
    movw Z, r24 ;move Z pointer to r24 
    
	lpm r21, Z
	
	load_next_character:
		; Load the next character from program memory into r23

	
		rcall encode_letter
	

		adiw Z, 1
		lpm r21, Z
		cpi r21, 0
		brne load_next_character
		rjmp end_of_message



    
    

	end_of_message:
		; Restore the modified registers and return
		
		pop ZL
		pop ZH
		pop r21
		pop r23
		pop r24
		pop r25
		ret



	


; ****************************************************
; **** END OF SECOND "STUDENT CODE" SECTION **********
; ****************************************************




; =============================================
; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; =============================================

; about one second
delay_long:
	push r16

	ldi r16, 14
delay_long_loop:
	rcall delay
	dec r16
	brne delay_long_loop

	pop r16
	ret


; about 0.25 of a second
delay_short:
	push r16

	ldi r16, 4
delay_short_loop:
	rcall delay
	dec r16
	brne delay_short_loop

	pop r16
	ret

; When wanting about a 1/5th of a second delay, all other
; code must call this function
;
delay:
	rcall delay_busywait
	ret


; This function is ONLY called from "delay", and
; never directly from other code. Really this is
; nothing other than a specially-tuned triply-nested
; loop. It provides the delay it does by virtue of
; running on a mega2560 processor.
;
delay_busywait:
	push r16
	push r17
	push r18

	ldi r16, 0x08
delay_busywait_loop1:
	dec r16
	breq delay_busywait_exit

	ldi r17, 0xff
delay_busywait_loop2:
	dec r17
	breq delay_busywait_loop1

	ldi r18, 0xff
delay_busywait_loop3:
	dec r18
	breq delay_busywait_loop2
	rjmp delay_busywait_loop3

delay_busywait_exit:
	pop r18
	pop r17
	pop r16
	ret


; Some tables


PATTERNS:
	; LED pattern shown from left to right: "." means off, "o" means
    ; on, 1 means long/slow, while 2 means short/fast.
	.db "A", "..oo..", 1
	.db "B", ".o..o.", 2
	.db "C", "o.o...", 1
	.db "D", ".....o", 1
	.db "E", "oooooo", 1
	.db "F", ".oooo.", 2
	.db "G", "oo..oo", 2
	.db "H", "..oo..", 2
	.db "I", ".o..o.", 1
	.db "J", ".....o", 2
	.db "K", "....oo", 2
	.db "L", "o.o.o.", 1
	.db "M", "oooooo", 2
	.db "N", "oo....", 1
	.db "O", ".oooo.", 1
	.db "P", "o.oo.o", 1
	.db "Q", "o.oo.o", 2
	.db "R", "oo..oo", 1
	.db "S", "....oo", 1
	.db "T", "..oo..", 1
	.db "U", "o.....", 1
	.db "V", "o.o.o.", 2
	.db "W", "o.o...", 2
	.db "W", "oo....", 2
	.db "Y", "..oo..", 2
	.db "Z", "o.....", 2
	.db "-", "o...oo", 1   ; Just in case!

WORD00: .db "HELLOWORLD", 0, 0
WORD01: .db "THE", 0
WORD02: .db "QUICK", 0
WORD03: .db "BROWN", 0
WORD04: .db "FOX", 0
WORD05: .db "JUMPED", 0, 0
WORD06: .db "OVER", 0, 0
WORD07: .db "THE", 0
WORD08: .db "LAZY", 0, 0
WORD09: .db "DOG", 0

; =======================================
; ==== END OF "DO NOT TOUCH" SECTION ====
; =======================================

